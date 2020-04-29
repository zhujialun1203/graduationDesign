package fun.zjlzjl.servicearticle.mapper;

import fun.zjlzjl.servicearticle.entity.ArticleDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */

public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {
    int updateArticleDetail(ArticleDetail articleDetail);
}
