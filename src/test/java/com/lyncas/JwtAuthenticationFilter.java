package com.lyncas;

import com.lyncas.infrastructure.security.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final JwtService jwtService;

  public JwtAuthenticationFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
    String token = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      String username = jwtService.extractUsername(token);
      if (username != null) {
        UserDetails userDetails = new User(username, "", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      }
    }
    return null;
  }
}