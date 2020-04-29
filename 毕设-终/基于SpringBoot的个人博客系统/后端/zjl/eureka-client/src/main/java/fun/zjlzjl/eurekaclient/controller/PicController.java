package fun.zjlzjl.eurekaclient.controller;


import fun.zjlzjl.eurekaclient.entity.Pic;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.service.IPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/pic")
public class PicController {
    @Autowired
    IPicService service;
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void  addPic(String picUrl,int uid) {
        Pic picEntity=new Pic();
        picEntity.setUserId(uid);
        picEntity.setUrl(picUrl);
        service.save(picEntity);

    };
    @RequestMapping(value = "/getPicByUserId/{userId}",method = RequestMethod.GET)
    public List<Pic> getPicByUserId(@PathVariable int userId) {
        System.out.println(userId);
        return service.getPicByUserId(userId);

    }

}
