package fun.zjlzjl.servicearticle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.zjlzjl.servicearticle.DTO.articleDTO;
import fun.zjlzjl.servicearticle.entity.Article;
import fun.zjlzjl.servicearticle.entity.ArticleDetail;
import fun.zjlzjl.servicearticle.mapper.ArticleDetailMapper;
import fun.zjlzjl.servicearticle.mapper.ArticleMapper;
import fun.zjlzjl.servicearticle.mapper.TagMapper;
import fun.zjlzjl.servicearticle.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleDetailMapper articleDetailMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    private HttpServletRequest request;

    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }
    public int getArticleCountByState(Integer state, Long uid,String keywords) {
        return articleMapper.getArticleCountByState(state,uid,keywords);
    }
    public List<articleDTO> getArticleByState(Integer state, Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        Long uid = Long.parseLong(request.getHeader("userId"));
        return articleMapper.getArticleByState(state, start, count, uid,keywords);
    }
    public List<articleDTO> getArticleByStateByGuest(Integer state, Integer page, Integer count, String keywords) {
        int start = (page - 1) * count;
        return articleMapper.getShowArticleByState(state, start, count,keywords);
    }

    public int updateArticleState(Long[] aids, Integer state) {
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }

    public int addNewArticle(articleDTO article) {
        ArticleDetail articleDetail=new ArticleDetail();
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //直接截取
            String stripHtml = stripHtml(article.getHtmlContent());
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        if (article.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(Long.parseLong(request.getHeader("userId")));

            int i = articleMapper.addNewArticle(article);

            articleDetail.setArticleId(article.getId().intValue());
            articleDetail.setHtmlContent(article.getHtmlContent());
            articleDetail.setMdContent(article.getMdContent());
            int j =articleDetailMapper.insert(articleDetail);

            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            articleDetail.setArticleId(article.getId().intValue());
            articleDetail.setHtmlContent(article.getHtmlContent());
            articleDetail.setMdContent(article.getMdContent());
            int j = articleDetailMapper.updateArticleDetail(articleDetail);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
    }

    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签
        tagMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库
        tagMapper.saveTags(dynamicTags);
        //3.查询这些标签的id
        List<Long> tIds = tagMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签
        int i = tagMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }
    public articleDTO getArticleById(Long aid) {
        articleDTO article = articleMapper.getArticleById(aid);
        QueryWrapper<ArticleDetail> qw=new QueryWrapper<>();
        qw.eq("articleId",aid);
        ArticleDetail articleDetail=articleDetailMapper.selectOne(qw);
        article.setHtmlContent(articleDetail.getHtmlContent());
        article.setMdContent(articleDetail.getMdContent());
        articleMapper.pvIncrement(aid);
        return article;
    }

    public int restoreArticle(Integer articleId) {
        return articleMapper.updateArticleStateById(articleId, 1); // 从回收站还原在原处
    }

    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

    /**
     * 获取最近七天的日期
     * @return
     */
    public List<String> getCategories() {
        return articleMapper.getCategories(Long.parseLong(request.getHeader("userId")));
    }

    /**
     * 获取最近七天的数据
     * @return
     */
    public List<Integer> getDataStatistics() {
        return articleMapper.getDataStatistics(Long.parseLong(request.getHeader("userId")));
    }
}
