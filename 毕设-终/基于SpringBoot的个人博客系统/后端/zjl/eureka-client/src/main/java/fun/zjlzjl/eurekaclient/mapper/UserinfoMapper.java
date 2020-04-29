package fun.zjlzjl.eurekaclient.mapper;

import fun.zjlzjl.eurekaclient.entity.Userinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
public interface UserinfoMapper extends BaseMapper<Userinfo> {
    int updateUserEmail(@Param("email") String email, @Param("id") Long id);
}
