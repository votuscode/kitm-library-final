package com.kitm.library.backend.spring.web.config.security;

import com.kitm.library.backend.spring.web.config.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Slf4j
@Configuration
@Order(1)
@RequiredArgsConstructor
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenFilter jwtTokenFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .antMatcher("/api/**")
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        // public endpoints
        .antMatchers("/api/public/**").permitAll()
        // private endpoints
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, exception) -> {
          log.error("Unauthorized request - {}", exception.getMessage());
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        });
  }
}
