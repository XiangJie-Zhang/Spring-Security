package com.example.demo.boot.config;

import com.example.demo.boot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义数据库认证
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    private UserInfoService userInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //构建用户信息的逻辑(取数据库/LDAP等用户信息)
        SelfUserDetails selfUserDetails = userInfoService.selectUserByName(username);
        if (selfUserDetails == null) {
            throw new UsernameNotFoundException("数据库中无此用户！");
        }
        return selfUserDetails;
    }

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
}
