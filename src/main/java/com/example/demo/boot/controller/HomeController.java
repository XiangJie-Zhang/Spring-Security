package com.example.demo.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页和登录页面映射
 */
@Controller
public class HomeController {
    @RequestMapping({"/", "/index1"})
    public String index(){
        return "index";
    }

    @RequestMapping("/login1")
    public String login(){
        return "login1";
    }
}
