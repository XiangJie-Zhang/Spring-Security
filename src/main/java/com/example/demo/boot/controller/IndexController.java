package com.example.demo.boot.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@Validated
public class IndexController {
    @GetMapping("/index")
    public String index(@NotBlank String zxj) {
        return "Hello World ~" + zxj;
    }

    @GetMapping("/success")
    public String index1() {
        return "登陆成功";
    }

    @GetMapping("/home")
    public String index2() {
        return "不需要i登录即可访问";
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo(@NotBlank String zxj) {
        return "忽略拦截的地方" + zxj;
    }

    @GetMapping("/login")
    public String getUserInfo1(@NotBlank String zxj) {
        return "登陆界面" + zxj;
    }

}
