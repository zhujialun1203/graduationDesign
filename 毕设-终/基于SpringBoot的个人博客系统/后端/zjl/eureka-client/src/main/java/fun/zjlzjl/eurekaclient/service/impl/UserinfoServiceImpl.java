package fun.zjlzjl.eurekaclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.User;
import fun.zjlzjl.eurekaclient.entity.Userinfo;
import fun.zjlzjl.eurekaclient.mapper.UserinfoMapper;
import fun.zjlzjl.eurekaclient.service.IUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Autowired
    private UserinfoMapper userInfoMapper;

    public Userinfo getUserinfo(userDto user)
    {
        QueryWrapper<Userinfo> ew = new QueryWrapper<>();
        ew.eq("userId" , user.getUserId());
        Userinfo userinfo = userInfoMapper.selectOne(ew);
        return userinfo;
    }
    @Override
    public String getCurrentUserNickNameById(Integer id) {
        return this.getById(id).getNickName();
    }
    @Override
    public int updateUser(Userinfo user) {
        userDto dto=new userDto();

        dto.setUserId(user.getUserId());
        Userinfo userinfo=this.getUserinfo(dto);
        userinfo.setNickName(user.getNickName());
        userinfo.setEmail(user.getEmail());
        userinfo.setQq(user.getQq());
        userinfo.setAvatar(user.getAvatar());
        if(userInfoMapper.updateById(userinfo)>0)
        {
            return 0;
        }
        return 2;
    }


}
