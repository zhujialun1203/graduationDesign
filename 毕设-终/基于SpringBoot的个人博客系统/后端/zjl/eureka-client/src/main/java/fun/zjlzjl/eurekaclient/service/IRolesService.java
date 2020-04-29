package fun.zjlzjl.eurekaclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.zjlzjl.eurekaclient.entity.Role;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
public interface IRolesService extends IService<Role> {
    int addRoles(String[] roles,Integer userId);
    Integer updateUserRoles(Long[] rids, Long id);
}
