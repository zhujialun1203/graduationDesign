package fun.zjlzjl.eurekaclient.mapper;

import fun.zjlzjl.eurekaclient.DTO.userDto;
import fun.zjlzjl.eurekaclient.entity.Role;
import fun.zjlzjl.eurekaclient.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
public interface UserMapper extends BaseMapper<User> {
    List<Role> getRolesByUid(long uid);
    List<userDto> getUserByNickname(String userName);
    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

}
