package com.harry.springk8sclient.config;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

}
