package fun.zjlzjl.servicearticle.service.impl;

import fun.zjlzjl.servicearticle.entity.Comment;
import fun.zjlzjl.servicearticle.mapper.CommentMapper;
import fun.zjlzjl.servicearticle.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
@Autowired
CommentMapper mapper;

    @Override
    public List<Comment> getCommentByAid(int aid) {
        return mapper.getCommentByAid(aid);
    }
}
