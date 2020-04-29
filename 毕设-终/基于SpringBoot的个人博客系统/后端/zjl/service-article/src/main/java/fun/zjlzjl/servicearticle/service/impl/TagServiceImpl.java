package fun.zjlzjl.servicearticle.service.impl;

import fun.zjlzjl.servicearticle.entity.Tag;
import fun.zjlzjl.servicearticle.mapper.TagMapper;
import fun.zjlzjl.servicearticle.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
