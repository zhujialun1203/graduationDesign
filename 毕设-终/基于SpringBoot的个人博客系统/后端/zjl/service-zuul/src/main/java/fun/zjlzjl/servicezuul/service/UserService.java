package fun.zjlzjl.servicezuul.service;

import fun.zjlzjl.servicezuul.DTO.userDto;
import fun.zjlzjl.servicezuul.bean.User;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "service-user")
public interface UserService {

    @RequestMapping(value = "/user/getUserByUserName",method = RequestMethod.GET)
    userDto getUserByUserName(@RequestParam(value = "userName") String userName);
    @RequestMapping(value = "/user/reg",method = RequestMethod.POST)
    int reg(@RequestBody userDto user);
}
