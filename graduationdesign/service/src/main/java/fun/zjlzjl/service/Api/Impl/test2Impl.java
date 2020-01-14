package fun.zjlzjl.service.Api.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import fun.zjlzjl.commonservicefacade.service.test2;

@Service(version = "2.0.0",timeout = 6000)
public class test2Impl implements test2 {

    @Override
    public String tt() {
        return "tt";
    }
}
