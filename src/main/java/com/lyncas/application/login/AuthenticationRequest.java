package com.lyncas.application.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Dados necessários para autenticação do usuário")
public class AuthenticationRequest {

  @ApiModelProperty(value = "Nome do usuário", required = true, example = "tiago")
  private String name;

  @ApiModelProperty(value = "Senha do usuário", required = true, example = "password123")
  private String password;
}