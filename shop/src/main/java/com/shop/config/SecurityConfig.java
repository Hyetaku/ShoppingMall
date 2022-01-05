package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//WebSecurityConfigurerAdapter 를 상속받은 클래스 SecurityConfig 에 어노테이션 선언하면
// SpringSecurityFilterChain 이 자동으로 포함됨 -> 메소드 오버라이딩을 통해 보안 설정을 커스터마이징 할 수 있음

    @Autowired
    MemberService memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http 요청에대해 보한 설정
        http.formLogin()
                .loginPage("/members/login") //로그인 페이지 URL 설정
                .defaultSuccessUrl("/") //로그인 성공시 이동할 URL
                .usernameParameter("email") //로그인 시 사용할 파라미터 이름을 email 로 지정
                .failureUrl("/members/login/error") //로그인 실패시 URL
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃 URL
                .logoutSuccessUrl("/"); //로그아웃 성공시 URL
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //비밀번호를 DB에 저장 했을 경우를 대비해 함수를 이용해 암호화(BCryptPasswordEncoder)를 빈으로 등록
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //스프링 시큐리티 인증은 AuthenticationManager 를 통해 이루어짐 Builder 가 생성
        //UserDetailService 를 구현하고 있는 객체는 memberService 를 지정해주고, 비밀번호 암호화
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }
}
