package fun.zjlzjl.servicearticle.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.zjlzjl.servicearticle.entity.Comment;
import fun.zjlzjl.servicearticle.entity.RespBean;
import fun.zjlzjl.servicearticle.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
@RestController
public class CommentController {
    @Autowired
    ICommentService service;

    @RequestMapping(value = "/comment/{aid}",method = RequestMethod.GET)
    public List<Comment> getComment(@PathVariable int aid){

        return service.getCommentByAid(aid);
    }
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public RespBean addComment(Comment comment) {
        boolean result=service.save(comment);
        if (result) {
            //成功
            return new RespBean("success", "成功!");
        }  else {
            //失败
            return new RespBean("error", "注册失败!");
        }
    }

}
