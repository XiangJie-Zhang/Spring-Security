package com.example.demo.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页和登录页面映射
 */
@Controller
// 使用hasRole会自动给角色设置ROLE_前缀，数据库最好也这么写，使用 @Secured数据库是什么就写什么
public class HomeController {
    //    @Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/user")
    public String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "user";
    }

    @RequestMapping("/admin")
//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "admin";
    }

    @RequestMapping("/public")
    public String public1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        return "public";
    }

    //    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @RequestMapping("/adminOrUser")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")  // hasRole自动加ROLE前缀
    public String adminOrUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return "adminOrUser";
    }
}
