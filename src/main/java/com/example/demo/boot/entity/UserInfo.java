package com.example.demo.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author zxj
 * @since 2019-12-29
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
    private Timestamp createTime;

    /**
     * name
     */
    @TableField("username")
    private String username;

    @TableField("time")
    private Timestamp time;


}
