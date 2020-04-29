package fun.zjlzjl.sericefeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hi")
public interface service {
    @RequestMapping(value = "/user/getUserByUserName",method = RequestMethod.GET)
    userDto getUserByUserName(@RequestParam(value = "userName") String userName);

    @RequestMapping(value = "/user/alive",method = RequestMethod.GET)
    String alive();
    @RequestMapping(value = "/user/getUser",method = RequestMethod.GET)
    String getUser();





}
