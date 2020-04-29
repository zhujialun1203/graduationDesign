package fun.zjlzjl.servicearticle.service;

import fun.zjlzjl.servicearticle.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.security.PublicKey;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
public interface ICommentService extends IService<Comment> {
     List<Comment> getCommentByAid(int aid);
}
