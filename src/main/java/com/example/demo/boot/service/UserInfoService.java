package com.example.demo.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.boot.auth.SelfUserDetails;
import com.example.demo.boot.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxj
 * @since 2020-01-01
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 通过用户名称搜索用户详细信息，包括权限信息，用于鉴权
     * @param username 用户名称
     * @return SelfUserDetails
     */
    SelfUserDetails selectUserByName(String username);
}
