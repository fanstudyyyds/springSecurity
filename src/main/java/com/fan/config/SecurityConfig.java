package com.fan.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//开启Security
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权的规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");


        //没有授权默认到登入页面
        http.formLogin().loginPage("/tologin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");
        //usernameParameter("username").passwordParameter("password")
        //参数
        //注销
        http.csrf().disable();//关闭csrf功能
        http.logout().logoutSuccessUrl("/");
        //.logoutUrl("/").deleteCookies().invalidateHttpSession(true)

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");//默认保存两周
        //rememberMeParameter记住我参数
    }


    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())//加密
                .withUser("fan").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2", "vip3").and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("vip1", "vip2", "vip3");
    }
}
