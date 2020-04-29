package fun.zjlzjl.eurekaclient.controller;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.tools.Klist;

import java.util.List;

@RestController
public class qiniuController {
        String accessKey = "850xj9a3rmr-syEfqyT0GRxszd8W4SGwecvQlfdR";
        String secretKey = "5i8WE9ASi4b0fxkt5lp0spXjvpP7geh_JZDgtptL";
        String bucket = "zjlcloud";
        Auth auth = Auth.create(accessKey, secretKey);
        Configuration cfg = new Configuration(Region.region0());
        BucketManager bucketManager = new BucketManager(auth, cfg);

        @RequestMapping("/getToken")
        public String getToken()
        {

                String upToken = auth.uploadToken(bucket);
                System.out.println(upToken);
                return upToken;
        }

        @RequestMapping("/getAllPhotoList")
        public void getPhotoList(){
                //文件名前缀
                String prefix = "";
                //每次迭代的长度限制，最大1000，推荐值 1000
                int limit = 1000;
                //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
                String delimiter = "";
                //列举空间文件列表
                BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
                while (fileListIterator.hasNext()) {
                        //处理获取的file list结果
                        FileInfo[] items = fileListIterator.next();
                        for (FileInfo item : items) {
                                System.out.println(item.key);
                                System.out.println(item.hash);
                                System.out.println(item.fsize);
                                System.out.println(item.mimeType);
                                System.out.println(item.putTime);
                                System.out.println(item.endUser);
                        }
                }
        }






}
