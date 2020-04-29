package fun.zjlzjl.eurekaclient.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.zjlzjl.eurekaclient.entity.Pic;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
public interface IPicService extends IService<Pic> {
   List<Pic> getPicByUserId(int userId);
}
