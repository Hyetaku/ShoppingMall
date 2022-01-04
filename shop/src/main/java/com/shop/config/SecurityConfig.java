package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//WebSecurityConfigurerAdapter 를 상속받은 클래스 SecurityConfig 에 어노테이션 선언하면
// SpringSecurityFilterChain 이 자동으로 포함됨 -> 메소드 오버라이딩을 통해 보안 설정을 커스터마이징 할 수 있음
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http 요청에대해 보한 설정
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //비밀번호를 DB에 저장 했을 경우를 대비해 함수를 이용해 암호화(BCryptPasswordEncoder)를 빈으로 등록
        return new BCryptPasswordEncoder();
    }
}
