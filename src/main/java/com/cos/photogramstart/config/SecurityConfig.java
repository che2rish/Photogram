package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IoC에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        // super.configure(http);

        // csrf 비활성화
        http.csrf().disable();
        // 1.
        http.authorizeRequests()
                // 해당 url로 요청시 403에러
                // 인증이 필요한 페이지
                .antMatchers("/","/user/**","/image/**","subscribe/**","/comment/**").authenticated()
                .anyRequest().permitAll()
                .and()
                // 인증이 필요한 페이지 요청이 오면 formLogin
                .formLogin()
                // formLogin 페이지가 -> /auth/signin
                .loginPage("/auth/signin")
                // 로그인을 정상적으로 처리하면 '/' 페이지로
                .defaultSuccessUrl("/");
    }
}
