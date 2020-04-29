package fun.zjlzjl.eurekaclient.controller;


import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import fun.zjlzjl.eurekaclient.entity.Userinfo;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
@RestController
@RequestMapping("/userInfo")
public class UserinfoController {
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/getUserInfo")
    public List<Userinfo> getUserInfo(){
       List<Userinfo> userinfoList = userinfoService.list();
        return userinfoList;
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public RespBean updateUserInfo(Userinfo user){
        System.out.println(user.toString());
        int result = userinfoService.updateUser(user);
        if (result == 0) {
            //成功
            return new RespBean("success", "修改成功!");
        }  else {
            //失败
            return new RespBean("error", "修改失败!");
        }
    }



}
