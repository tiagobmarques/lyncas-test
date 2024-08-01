package com.lyncas.domain.security;

import com.lyncas.application.login.AuthenticationRequest;
import com.lyncas.domain.usuario.UsuarioEntity;
import com.lyncas.infrastructure.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public UsuarioEntity signup(AuthenticationRequest input) {
    UsuarioEntity user = UsuarioEntity.builder()
        .name(input.getName())
        .password(passwordEncoder.encode(input.getPassword()))
        .build();

    return usuarioRepository.save(user);
  }

  public UsuarioEntity authenticate(AuthenticationRequest input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            input.getName(),
            input.getPassword()
        )
    );

    return usuarioRepository.findByName(input.getName())
        .orElseThrow();
  }
}