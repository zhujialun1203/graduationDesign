package fun.zjlzjl.servicearticle.mapper;

import fun.zjlzjl.servicearticle.DTO.articleDTO;
import fun.zjlzjl.servicearticle.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
public interface ArticleMapper extends BaseMapper<Article> {
    int addNewArticle(articleDTO article);
    int updateArticle(articleDTO article);

    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);
    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);
    List<articleDTO> getArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);
    List<articleDTO> getShowArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count,@Param("keywords") String keywords);
    int deleteArticleById(@Param("aids") Long[] aids);

    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);
    articleDTO getArticleById(Long aid);
    List<String> getCategories(Long uid);

    List<Integer> getDataStatistics(Long uid);
    void pvStatisticsPerDay();
    void pvIncrement(Long aid);
}
