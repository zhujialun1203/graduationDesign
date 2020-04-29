package fun.zjlzjl.servicearticle.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer Id;
    @TableField("userId")
    private Integer userId;

    private String title;

    private Integer cid;

    private String summary;

    @TableField("publishTime")
    private LocalDateTime publishTime;

    @TableField("editTime")
    private LocalDateTime editTime;

    private Integer state;

    @TableField("pageView")
    private Integer pageView;


}
