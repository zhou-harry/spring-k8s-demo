package com.harry.springk8sclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

  @GetMapping("/auth")
  public String getServer(String code) {
    return code;
  }
}
