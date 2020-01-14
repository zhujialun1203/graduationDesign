package fun.zjlzjl.service.Api.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import fun.zjlzjl.commonservicefacade.service.test;

@Service(version = "1.0.0",timeout = 3000,dynamic = true)
public class testImpl implements test {

    @Override
    public void test() {
        System.out.println("test");
    }
}
