package fun.zjlzjl.servicearticle.mapper;

import fun.zjlzjl.servicearticle.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
public interface CommentMapper extends BaseMapper<Comment> {
     List<Comment> getCommentByAid(int aid);
}
