package com.gmf._5.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .cors((AbstractHttpConfigurer::disable));

        httpSecurity
            .authorizeHttpRequests(request -> request
                .requestMatchers("/guestbook/**").permitAll()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().authenticated())
            .formLogin(form -> form
                .permitAll()
                .defaultSuccessUrl("/admin", true));

        return httpSecurity.build();
    }

    // 메모리 사용자 등록
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
            .username("admin")                           // 원하는 아이디
            .password(passwordEncoder.encode("labchasm2025!"))    // 원하는 패스워드
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
