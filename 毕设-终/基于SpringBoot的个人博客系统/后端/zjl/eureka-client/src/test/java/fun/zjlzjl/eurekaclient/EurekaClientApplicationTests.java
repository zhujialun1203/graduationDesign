package fun.zjlzjl.eurekaclient;





import fun.zjlzjl.eurekaclient.controller.UserinfoController;
import fun.zjlzjl.eurekaclient.controller.userController;
import fun.zjlzjl.eurekaclient.mapper.RolesMapper;
import fun.zjlzjl.eurekaclient.mapper.UserMapper;
import fun.zjlzjl.eurekaclient.mapper.UserinfoMapper;
import fun.zjlzjl.eurekaclient.service.IUserService;
import fun.zjlzjl.eurekaclient.service.IUserinfoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class EurekaClientApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    IUserinfoService userinfoService;
        @Test
        public void test()
        {
            System.out.println("*********************test***********************");
            System.out.println(userinfoService.getById(27));
            System.out.println("*********************testEnd***********************");
        }
}
