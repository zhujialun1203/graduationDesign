package fun.zjlzjl.eurekaclient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.zjlzjl.eurekaclient.entity.Role;
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
public interface RolesMapper extends BaseMapper<Role> {
    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);

    List<Role> getRolesByUid(Long uid);
    int deleteUserRolesByUid(Long id);
    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);
}
