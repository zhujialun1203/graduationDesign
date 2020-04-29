package fun.zjlzjl.eurekaclient.controller;

import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.Notice;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.entity.Role;
import fun.zjlzjl.eurekaclient.entity.User;
import fun.zjlzjl.eurekaclient.mapper.RolesMapper;
import fun.zjlzjl.eurekaclient.service.INoticeService;
import fun.zjlzjl.eurekaclient.service.IRolesService;
import fun.zjlzjl.eurekaclient.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRolesService rolesService;
    @Autowired
    INoticeService noticeService;

    @RequestMapping(value = "/notice",method = RequestMethod.PUT)
    public RespBean updateNotice(String notice){
        if(noticeService.updateById(new Notice(1,notice, new Timestamp(System.currentTimeMillis()))))
        {
            return new RespBean("success", "更新成功!");
        }
        else {
            return new RespBean("error", "更新失败!");
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<userDto> getUserByNickname(@RequestParam(value = "nickname") String nickname) {

        return userService.getUserByNickname(nickname);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getAllRole() {
        return rolesService.list();
    }

    @RequestMapping(value = "/user/enabled", method = RequestMethod.PUT)
    public RespBean updateUserEnabled(Boolean enabled, Long uid) {

        if (userService.updateUserEnabled(enabled, uid) == 1) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public RespBean deleteUserById(@PathVariable int id)
    {
        int result = userService.deleteUser(id);
        if (result == 0) {
            //成功
            return new RespBean("success", "删除成功!");
        } else if (result == 1) {
            return new RespBean("error", "id未找到，删除失败!");
        } else {
            //失败
            return new RespBean("error", "删除失败!");
        }
    }
    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public RespBean updateUserRoles(Long[] rids, Long id) {
        if (rolesService.updateUserRoles(rids, id) == rids.length) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }


}
