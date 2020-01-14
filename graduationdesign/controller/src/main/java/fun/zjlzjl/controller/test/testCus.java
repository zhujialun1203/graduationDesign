package fun.zjlzjl.controller.test;


import com.alibaba.dubbo.config.annotation.Reference;
import fun.zjlzjl.commonservicefacade.service.test2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testCus {
    @Reference(version = "2.0.0")
    private test2 tt;
    @RequestMapping("/test")
    public String test()
    {
        return tt.tt();
    }
}
