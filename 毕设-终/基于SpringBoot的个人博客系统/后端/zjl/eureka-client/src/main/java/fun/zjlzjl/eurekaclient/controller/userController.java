package fun.zjlzjl.eurekaclient.controller;

import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.entity.User;
import fun.zjlzjl.eurekaclient.entity.Userinfo;
import fun.zjlzjl.eurekaclient.service.IRolesService;
import fun.zjlzjl.eurekaclient.service.IUserService;
import fun.zjlzjl.eurekaclient.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRolesService iRolesService;
    @Autowired
    private IUserinfoService iUserinfoService;
    @RequestMapping("/alive")
    public String t(){
        return "ok";
    }

    @RequestMapping("/getUserByUserName")
    public userDto getUserByUserName(@RequestParam(value = "userName") String username){
        userDto user = userService.getUserByUserName(username);
        return  user;
    }

    @RequestMapping(value = "/updateUserEmail",method = RequestMethod.PUT)
    public RespBean updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            return new RespBean("success", "开启成功!");
        }
        return new RespBean("error", "开启失败!");
    }

    @RequestMapping("/reg")
    public int reg(userDto user) {
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassWord(DigestUtils.md5DigestAsHex(user.getPassWord().getBytes()));
        user1.setEnable(1);
        int result = userService.reg(user1);
        if(result==1)
        {
            return 1;
        }
        String[] roles = new String[]{"2"};
        if(user.getUserId()==null)
        {
            user1.setId(userService.getIdByUserName(user.getUserName()));
        }
        int i = iRolesService.addRoles(roles, user1.getId());
        boolean b = i == roles.length && result == 0;
        if (b) {
            Userinfo userinfo=new Userinfo();
            userinfo.setNickName("用户"+user1.getId());
            userinfo.setUserId(user1.getId());
            userinfo.setRegTime(new Date());
            iUserinfoService.save(userinfo);
            return 0;
        } else {
            return 2;
        }

    }





}
