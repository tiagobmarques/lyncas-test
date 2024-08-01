package com.lyncas.application.login;

import com.lyncas.domain.security.AuthenticationService;
import com.lyncas.domain.usuario.UsuarioEntity;
import com.lyncas.infrastructure.security.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Autenticação")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

  private final JwtService jwtService;
  private final AuthenticationService authenticationService;

  @ApiOperation(value = "Registra um novo usuário")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Usuário registrado com sucesso"),
      @ApiResponse(code = 400, message = "Dados inválidos fornecidos"),
      @ApiResponse(code = 500, message = "Erro interno do servidor")
  })
  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(
      @RequestBody @ApiParam(value = "Dados de registro do usuário", required = true) AuthenticationRequest authenticationRequest) {
    authenticationService.signup(authenticationRequest);
  }

  @ApiOperation(value = "Autentica um usuário e retorna um token JWT")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Usuário autenticado com sucesso", response = AuthenticationResponse.class),
      @ApiResponse(code = 401, message = "Credenciais inválidas fornecidas"),
      @ApiResponse(code = 500, message = "Erro interno do servidor")
  })
  @PostMapping("/login")
  public AuthenticationResponse authenticate(
      @RequestBody @ApiParam(value = "Dados de autenticação do usuário", required = true) AuthenticationRequest authenticationRequest) {

    UsuarioEntity authenticatedUser = authenticationService.authenticate(authenticationRequest);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .expiresIn(jwtService.getExpirationTime())
        .build();
  }
}
