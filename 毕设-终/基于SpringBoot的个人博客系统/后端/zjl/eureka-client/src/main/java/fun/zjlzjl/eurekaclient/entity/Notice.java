package fun.zjlzjl.eurekaclient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhujialun
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;
    public Notice(){

    }
    public Notice(int id,String Notice,Timestamp editDate){
        this.id=(long)id;
        this.notice=Notice;
        this.editDate=editDate;
    }


    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String notice;

    @TableField("editDate")
    private Timestamp editDate;


}
