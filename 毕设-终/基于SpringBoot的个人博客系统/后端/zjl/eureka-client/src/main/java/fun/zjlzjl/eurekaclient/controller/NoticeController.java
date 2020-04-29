package fun.zjlzjl.eurekaclient.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.zjlzjl.eurekaclient.entity.Notice;
import fun.zjlzjl.eurekaclient.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService noticeService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Notice getNotice(){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id","1");
        return noticeService.getOne(queryWrapper);
    }

}
