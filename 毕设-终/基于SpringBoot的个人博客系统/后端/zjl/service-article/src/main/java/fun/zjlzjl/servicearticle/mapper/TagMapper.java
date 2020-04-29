package fun.zjlzjl.servicearticle.mapper;

import fun.zjlzjl.servicearticle.entity.Tag;
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
 * @since 2020-04-08
 */

public interface TagMapper extends BaseMapper<Tag> {
    int deleteTagsByAid(Long aid);

    int saveTags(@Param("tags") String[] tags);

    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}
