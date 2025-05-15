package com.ho.bootframework.auth.config;

import com.ho.bootframework.auth.jwt.JwtAuthenticationFilter;
import com.ho.bootframework.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
  private final JwtProvider jwtProvider;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(jwtProvider);
  }
}
