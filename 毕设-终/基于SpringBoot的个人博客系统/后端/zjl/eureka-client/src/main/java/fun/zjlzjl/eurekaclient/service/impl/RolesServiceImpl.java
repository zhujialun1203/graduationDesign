package fun.zjlzjl.eurekaclient.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.zjlzjl.eurekaclient.entity.Role;
import fun.zjlzjl.eurekaclient.mapper.RolesMapper;
import fun.zjlzjl.eurekaclient.service.IRolesService;
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
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Role> implements IRolesService {
    @Autowired
    RolesMapper rolesMapper;

    @Override
    public int addRoles(String[] roles, Integer userId) {
        return rolesMapper.addRoles(roles,userId.longValue());
    }

    @Override
    public Integer updateUserRoles(Long[] rids, Long id) {
        int i = rolesMapper.deleteUserRolesByUid(id);
        System.out.println("ROLE DElELETE-----+"+i);
        return rolesMapper.setUserRoles(rids, id);
    }


}
