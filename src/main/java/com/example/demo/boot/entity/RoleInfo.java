package com.example.demo.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId("pk_ri_id")
    private String pkRiId;

    /**
     * 角色类型，0：普通，1：管理；2：超级
     */
    @TableField("type")
    private Integer type;

    /**
     * 删除状态0：未删除；1已删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;


}
