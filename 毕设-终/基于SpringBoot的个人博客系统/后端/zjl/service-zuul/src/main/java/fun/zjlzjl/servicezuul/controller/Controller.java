package fun.zjlzjl.servicezuul.controller;

import fun.zjlzjl.servicezuul.DTO.userDto;
import fun.zjlzjl.servicezuul.bean.RespBean;
import fun.zjlzjl.servicezuul.bean.User;
import fun.zjlzjl.servicezuul.service.UserService;
import fun.zjlzjl.servicezuul.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @RequestMapping("/login_page")
    public String index(){
        return "请先登录";
    }


    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public RespBean reg(String userName,String passWord) {
        System.out.println("username----"+userName+"\npassWord------"+passWord);

        userDto user=new userDto();
        user.setUserName(userName);
        user.setPassWord(passWord);
        int result = userService.reg(user);
        if (result == 0) {
            //成功
            return new RespBean("success", "注册成功!");
        } else if (result == 1) {
            return new RespBean("error", "用户名重复，注册失败!");
        } else {
            //失败
            return new RespBean("error", "注册失败!");
        }
    }


    @RequestMapping("/currentUserNickName")
    public String currentUserName(){
        return Util.getCurrentUser().getNickName();
    }

    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }
    @RequestMapping("/getMyUserinfo")
    public userDto getMyUserinfo() {
        return userService.getUserByUserName(Util.getCurrentUser().getUsername());
    }


    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }


}
