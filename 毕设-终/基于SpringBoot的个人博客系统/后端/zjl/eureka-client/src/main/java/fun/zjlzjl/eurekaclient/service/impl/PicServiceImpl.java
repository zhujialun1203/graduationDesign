package fun.zjlzjl.eurekaclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.zjlzjl.eurekaclient.entity.Pic;
import fun.zjlzjl.eurekaclient.mapper.PicMapper;
import fun.zjlzjl.eurekaclient.service.IPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
@Service
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements IPicService {
    @Autowired
    PicMapper mapper;
    @Override
    public List<Pic> getPicByUserId(int userId) {
        QueryWrapper<Pic> qw=new QueryWrapper<>();
        qw.eq("userId",userId);
        return mapper.selectList(qw);
    }
}
