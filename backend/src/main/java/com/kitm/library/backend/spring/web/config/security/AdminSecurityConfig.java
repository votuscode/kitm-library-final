package com.kitm.library.backend.spring.web.config.security;

import com.kitm.library.backend.domain.authentication.AuthenticationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 20.02.22
 */
@Slf4j
@Configuration
@Order(2)
@RequiredArgsConstructor
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthenticationUserService authenticationUserService;

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${authentication.cookie}")
  private String cookieName;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .defaultSuccessUrl("/")
        .and()
        .logout()
        .logoutSuccessUrl("/login?logout")
        .deleteCookies(cookieName)
        .and()
        .rememberMe()
        .rememberMeServices(getRememberMeServices())
        .key(jwtSecret)
        .and()
        .authorizeRequests()
        .antMatchers("/login", "/register", "/403", "/404", "/500").permitAll()
        .anyRequest().authenticated();
  }

  private TokenBasedRememberMeServices getRememberMeServices() {

    TokenBasedRememberMeServices services = new TokenBasedRememberMeServices(jwtSecret, authenticationUserService);

    services.setCookieName(cookieName);
    services.setTokenValiditySeconds(3000);

    return services;
  }
}
