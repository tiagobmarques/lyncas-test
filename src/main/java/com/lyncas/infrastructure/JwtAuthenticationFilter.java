package com.lyncas.infrastructure;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final String jwtSecret;

  public JwtAuthenticationFilter(String jwtSecret) {
    this.jwtSecret = jwtSecret;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) {
    // Não aplicável para a autenticação de JWT
    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    String token = Jwts.builder()
        .setSubject(((User) authResult.getPrincipal()).getUsername())
        .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
        .compact();
    response.addHeader("Authorization", "Bearer " + token);
  }
}