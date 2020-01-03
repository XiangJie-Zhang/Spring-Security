package com.example.demo.lianxi.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * spring security 配置类
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 手动创建用户放在内存里
     *
     * @return UserDetailsService 用户登录逻辑
     */
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user").password("123").roles("admin").build());
        return manager;
    }


    /**
     * 配置，哪些链接可以访问，哪些需要什么处理
     *
     * @param http HTTP
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/hello").hasRole("user")  // hello界面只允许user用户登录
                .antMatchers("/index").permitAll()      // index界面所有人都可以，不登陆也能访问
                .and().formLogin()                      // 因为没有其他配置，这两个默认调用自带的
                .and().httpBasic();
    }
}
