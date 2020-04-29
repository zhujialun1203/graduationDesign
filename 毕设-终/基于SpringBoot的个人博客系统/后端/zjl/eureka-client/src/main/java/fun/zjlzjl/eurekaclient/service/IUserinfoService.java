package fun.zjlzjl.eurekaclient.service;

import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
public interface IUserinfoService extends IService<Userinfo> {
    int updateUser(Userinfo user);
    String getCurrentUserNickNameById(Integer id);
}
