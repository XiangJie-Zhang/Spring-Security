package com.example.demo.boot.config;


import com.example.demo.boot.auth.*;
import com.example.demo.boot.filter.TokenAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * 前两个，必要注解
 * 开启验证
 * 开启其他注解
 */

// securedEnabled=true, 开启 @Secured 注解.
//  @Secured("ROLE_USER")，这个注解中的角色就是数据库中的角色，应该与数据库一致
// prePostEnabled=true, 开启 prePostEnabled 相关的注解.
// @PreAuthorize("hasRole('ADMIN')")，这个注解使用了hasRole，凡是使用这个方法的，会自动对参数加ROLE_前缀；这里就不需要自己加了
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {


    private AjaxAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;  // 登录成功返回的 JSON// 格式数据给前端（否则为 html）
    private AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）
    private AjaxLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
    private AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
    private SelfUserDetailsService userDetailsService; // 自定义user
    private TokenAuthorizationFilter tokenAuthorizationFilter; // 自定义jwt token验证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 去掉 CSRF
        http.cors()
                .and()
                .csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())  // 开启csrf
                // ，并把csrf放如session中
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()
                // 未登录时返回信息
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()
                .antMatchers("/public").permitAll()

                .and()
                .formLogin()  //开启登录
                .successHandler(ajaxAuthenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService).tokenValiditySeconds(300);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
        http.addFilterBefore(tokenAuthorizationFilter,
                UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }

    @Autowired
    public void setAuthenticationEntryPoint(AjaxAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setAuthenticationSuccessHandler(AjaxAuthenticationSuccessHandler authenticationSuccessHandler) {
        this.ajaxAuthenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Autowired
    public void setAuthenticationFailureHandler(AjaxAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Autowired
    public void setLogoutSuccessHandler(AjaxLogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Autowired
    public void setAccessDeniedHandler(AjaxAccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setUserDetailsService(SelfUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Autowired
    public void setTokenAuthorizationFilter(TokenAuthorizationFilter tokenAuthorizationFilter) {
        this.tokenAuthorizationFilter = tokenAuthorizationFilter;
    }
}
