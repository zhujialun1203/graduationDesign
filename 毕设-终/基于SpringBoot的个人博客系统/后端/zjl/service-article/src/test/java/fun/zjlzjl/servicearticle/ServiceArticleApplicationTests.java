package fun.zjlzjl.servicearticle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.zjlzjl.servicearticle.entity.Article;
import fun.zjlzjl.servicearticle.entity.Tag;
import fun.zjlzjl.servicearticle.mapper.ArticleMapper;
import fun.zjlzjl.servicearticle.mapper.TagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

@SpringBootTest
class ServiceArticleApplicationTests {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagMapper tagMapper;
    @Test
    void contextLoads() {
        QueryWrapper<Tag> qw=new QueryWrapper<>();
       System.out.println(tagMapper.selectCount(qw));
    }

}
