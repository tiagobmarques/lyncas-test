package com.lyncas.application.login;

import lombok.Data;

@Data
public class AuthRequest {
  private String username;
  private String password;
}