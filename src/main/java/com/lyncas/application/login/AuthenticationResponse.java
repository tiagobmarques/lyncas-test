package com.lyncas.application.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Resposta de autenticação contendo o token JWT e o tempo de expiração")
public class AuthenticationResponse {

  @ApiModelProperty(value = "Token JWT gerado para autenticação do usuário", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0aWFnbyIsInJvbGUiOiJVU0VSIn0.eyJzdWIiOiJ0aWFnbyIsInJvbGUiOiJVU0VSIn0.ZhZSMzFGY3dveFAwWENZl1d0aS4oh4n_xJ6BfZDWXvE", required = true)
  private String token;

  @ApiModelProperty(value = "Tempo de expiração do token em milissegundos", example = "3600000", required = true)
  private long expiresIn;
}