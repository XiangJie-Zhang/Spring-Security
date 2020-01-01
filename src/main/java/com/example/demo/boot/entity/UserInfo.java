package com.example.demo.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @author zxj
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("pk_ui_id")
    private String pkUiId;

    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * name
     */
    @TableField("username")
    private String username;

    @TableField("time")
    private LocalDateTime time;

    @TableField("password")
    private String password;


}
