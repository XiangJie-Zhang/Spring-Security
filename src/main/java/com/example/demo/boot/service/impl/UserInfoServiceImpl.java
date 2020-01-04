package com.example.demo.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.boot.auth.SelfUserDetails;
import com.example.demo.boot.entity.UserInfo;
import com.example.demo.boot.mapper.UserInfoMapper;
import com.example.demo.boot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxj
 * @since 2020-01-01
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    private UserInfoMapper userInfoMapper;

    @Override
    public SelfUserDetails selectUserByName(String username) {
        return userInfoMapper.selectUserByName(username);
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }
}
