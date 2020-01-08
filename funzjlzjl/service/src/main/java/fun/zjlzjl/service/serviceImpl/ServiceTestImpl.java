package fun.zjlzjl.service.serviceImpl;


import fun.zjlzjl.commonservicefacade.service.serviceTest;
import org.springframework.stereotype.Service;

@Service("ser")
public class ServiceTestImpl implements serviceTest {

    @Override
    public String test() {
        return "hello";
    }
}
