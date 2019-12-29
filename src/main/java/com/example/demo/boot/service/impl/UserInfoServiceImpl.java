package com.example.demo.boot.service.impl;

import com.example.demo.boot.entity.UserInfo;
import com.example.demo.boot.mapper.UserInfoMapper;
import com.example.demo.boot.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxj
 * @since 2019-12-29
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
