package fun.zjlzjl.MysqlGenerator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userId")
    private Integer userId;

    private String url;


}
