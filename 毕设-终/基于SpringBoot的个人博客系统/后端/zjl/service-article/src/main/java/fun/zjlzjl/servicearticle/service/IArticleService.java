package fun.zjlzjl.servicearticle.service;

import fun.zjlzjl.servicearticle.DTO.articleDTO;
import fun.zjlzjl.servicearticle.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
public interface IArticleService extends IService<Article> {
    int getArticleCountByState(Integer state, Long uid,String keywords);
    List<articleDTO> getArticleByState(Integer state, Integer page, Integer count, String keywords);
    List<articleDTO> getArticleByStateByGuest(Integer state, Integer page, Integer count, String keywords);
    int updateArticleState(Long[] aids, Integer state);
    int addNewArticle(articleDTO article);
    articleDTO getArticleById(Long aid);
    int restoreArticle(Integer articleId);

    /**
     * 获取最近七天的日期
     * @return
     */
    List<String> getCategories();

    /**
     * 获取最近七天的数据
     * @return
     */
    List<Integer> getDataStatistics();
    void pvStatisticsPerDay();

}
