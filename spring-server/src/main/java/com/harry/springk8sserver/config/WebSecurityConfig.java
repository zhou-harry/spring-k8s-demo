package com.harry.springk8sserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * 忽略静态资源和 Swagger 等资源的 token 检查
   *
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/static/**", "/", "/csrf")
        .antMatchers("/v2/api-docs", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**","/**")
        .antMatchers("/favicon.ico", "/actuator/**");
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails userDetails = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(userDetails);
  }
}
