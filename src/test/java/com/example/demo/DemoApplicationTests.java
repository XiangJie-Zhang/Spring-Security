package com.example.demo;

import com.example.demo.boot.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {

    @Autowired
    UserInfoMapper userInfoMapper;
    public static String generatePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Test
    void contextLoads() {
        System.out.println(generatePassword("123"));
    }

}
