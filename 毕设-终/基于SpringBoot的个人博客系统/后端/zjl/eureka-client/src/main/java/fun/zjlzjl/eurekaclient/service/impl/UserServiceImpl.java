package fun.zjlzjl.eurekaclient.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.entity.Role;
import fun.zjlzjl.eurekaclient.entity.User;
import fun.zjlzjl.eurekaclient.entity.Userinfo;
import fun.zjlzjl.eurekaclient.mapper.UserMapper;
import fun.zjlzjl.eurekaclient.mapper.UserinfoMapper;
import fun.zjlzjl.eurekaclient.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hibernate.validator.internal.util.logging.Log_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    public UserMapper userMapper;
    @Autowired
    private UserinfoMapper userInfoMapper;
    @Autowired
    private HttpServletRequest request;

    @Override
    public int updateUserEnabled(boolean enabled, long uid) {
        return userMapper.updateUserEnabled(enabled, uid);

    }

    @Override
    public List<userDto> getUserByNickname(String userName) {
        List<userDto> list=userMapper.getUserByNickname(userName);

        return list;
    }
    @Override
    public Integer getIdByUserName(String userName)
    {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("username" , userName);
        User user1 = userMapper.selectOne(ew);
        return user1.getId();
    }



    @Override
    public userDto getUserByUserName(String userName) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("username" , userName);
        User user1 = userMapper.selectOne(ew);
        QueryWrapper<Userinfo> ew2 = new QueryWrapper<>();
        ew2.eq("userId" , user1.getId());
        Userinfo userInfo=userInfoMapper.selectOne(ew2);
        userDto dto=new userDto();

        dto.setUserId(user1.getId());
        dto.setUserName(user1.getUserName());
        dto.setPassWord(user1.getPassWord());
        dto.setEnabled(user1.getEnable()==1);
        dto.setNickName(userInfo.getNickName());
        dto.setQq(userInfo.getQq());
        dto.setAvatar(userInfo.getAvatar());
        dto.setEmail(userInfo.getEmail());
        dto.setRegTime(userInfo.getRegTime().toString());
        List<Role> roles = userMapper.getRolesByUid(user1.getId());
        dto.setRoles(roles);
        return dto;
    }

    @Override
    public int reg(User user) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sf.format(new Date());
        Date parse = null;
        try {
            parse = sf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName,user.getUserName());
        if (userMapper.selectCount(queryWrapper)>=1)
        {
            return 1;
        }
        User user1 = new User();
        user1.setUserName(user.getUserName());
        user1.setPassWord(user.getPassWord());
        if(userMapper.insert(user1)>0)
        {
            return 0;
        }
        return 2;


    }

    @Override
    public int updateUser(userDto user) {
        QueryWrapper<User> ew = new QueryWrapper<>();
        ew.eq("userId" , user.getUserId());
        User user1 = userMapper.selectOne(ew);
        user1.setPassWord(user.getPassWord());
        if(userMapper.updateById(user1)>0)
        {
            return 0;
        }
        return 2;
    }

    @Override
    public int deleteUser(int userId) {
        if(userMapper.deleteById(userId)>0)
        {
            return 0;
        }
        return 2;

    }

    public int updateUserEmail(String email) {
        return userInfoMapper.updateUserEmail(email, Long.parseLong(request.getHeader("userId")));
    }

}
