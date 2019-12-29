package com.example.demo.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "Hello World ~";
    }

    @GetMapping("/success")
    public String index1() {
        return "登陆成功";
    }

    @GetMapping("/home")
    public String index2() {
        return "不需要i登录即可访问";
    }

}
