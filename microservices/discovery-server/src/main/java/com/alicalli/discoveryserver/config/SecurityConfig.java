package com.alicalli.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${eureka.username}")
  private String username;

  @Value("${eureka.password}")
  private String password;

  @Override
  public void configure(
    AuthenticationManagerBuilder authenticationManagerBuilder
  ) throws Exception {
    authenticationManagerBuilder
      .inMemoryAuthentication()
      .passwordEncoder(NoOpPasswordEncoder.getInstance())
      .withUser(username)
      .password(password)
      .roles("USER");
  }


  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
