package fun.zjlzjl.servicearticle.service;

import fun.zjlzjl.servicearticle.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
public interface ICategoryService extends IService<Category> {
    List<Category> getAllCategories();
    boolean deleteCategoryByIds(String ids);
    int updateCategoryById(Category category);
    int addCategory(Category category);
}
