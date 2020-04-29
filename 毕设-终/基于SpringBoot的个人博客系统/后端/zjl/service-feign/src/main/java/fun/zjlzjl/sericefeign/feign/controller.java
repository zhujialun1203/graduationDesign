package fun.zjlzjl.sericefeign.feign;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
        String accessKey = "850xj9a3rmr-syEfqyT0GRxszd8W4SGwecvQlfdR";
        String secretKey = "5i8WE9ASi4b0fxkt5lp0spXjvpP7geh_JZDgtptL";
        String bucket = "zjlcloud";

        //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
        @Autowired
        private service schedualServiceHi;

        @GetMapping(value = "/hi")
        public userDto sayHi(@RequestParam String name) {
            return schedualServiceHi.getUserByUserName( name );
        }
        @GetMapping(value = "/user/getUser")
        public String getUser() {
                return schedualServiceHi.getUser();
        }

        @RequestMapping("/getToken")
        public String getToken()
        {
                Auth auth = Auth.create(accessKey, secretKey);
                String upToken = auth.uploadToken(bucket);
                System.out.println(upToken);
                return upToken;
        }


}
