package com.edu.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
				.csrf((auth) -> auth.disable());
		http
				.formLogin((auth) -> auth.disable());
		http
				.httpBasic((auth) -> auth.disable());
		//경로별 인가 작업 
		http
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/login", "/", "/join").permitAll() //로그인 , 루트, 회원가입 누구나 접근가능 
						.requestMatchers("/admin").hasRole("ADMIN") //어드민만 접근 가능  
						.anyRequest().authenticated()); //authenticated() = 로그인한 사용자만 접근 가능
		//세션 설정 
		http
				.sessionManagement((session) ->session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //STATELESS 상태로 세션을 관리하기 위한 설정 
		
		return http.build();
	}
}
