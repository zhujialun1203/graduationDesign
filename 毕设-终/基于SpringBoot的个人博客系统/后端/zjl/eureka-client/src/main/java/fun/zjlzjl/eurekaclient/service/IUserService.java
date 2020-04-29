package fun.zjlzjl.eurekaclient.service;

import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.RespBean;
import fun.zjlzjl.eurekaclient.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
public interface IUserService extends IService<User> {
    int updateUserEnabled(boolean enabled, long uid);
    List<userDto> getUserByNickname(String userName);
    userDto getUserByUserName(String userName);
    int reg(User user);
    int deleteUser(int userId);
    int updateUser(userDto user);
    Integer getIdByUserName(String userName);
    int updateUserEmail(String email);
}
