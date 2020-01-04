package com.example.demo.boot.mapper;

import com.example.demo.boot.auth.SelfUserDetails;
import com.example.demo.boot.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxj
 * @since 2020-01-01
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 通过用户名称搜索用户详细信息，包括权限信息，用于鉴权
     * @param username 用户名称
     * @return SelfUserDetails
     */
    SelfUserDetails selectUserByName(@Param("username") String username);
}
