package fun.zjlzjl.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import fun.zjlzjl.commonservicefacade.service.serviceTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class testController {

    @Reference
    private serviceTest userService;

    @RequestMapping("test")
    public String test()
    {
        return userService.test();
    }
}
