package fun.zjlzjl.eurekaclient.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhujialun
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer Id;

    @TableField("userId")
    private Integer userId;

    @TableField("nickName")
    private String nickName;

    @TableField("QQ")
    private String qq;
    @TableField("email")
    private String email;
    @TableField("avatar")
    private String avatar;

    private Date regTime;
    public String toString(){
        return "QQ"+this.qq+"\n email"+this.email+"\n avatar"+this.avatar;
    }


}
