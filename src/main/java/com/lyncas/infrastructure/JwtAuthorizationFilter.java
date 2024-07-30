package com.lyncas.infrastructure;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final String jwtSecret;
  private final UserDetailsService userDetailsService;

  public JwtAuthorizationFilter(String jwtSecret, UserDetailsService userDetailsService) {
    this.jwtSecret = jwtSecret;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
      String token = header.replace("Bearer ", "");
      try {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret.getBytes())
            .parseClaimsJws(token)
            .getBody();

        String username = claims.getSubject();

        if (username != null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (Exception e) {
        // Log exception
        System.out.println("Invalid JWT token: " + e.getMessage());
        SecurityContextHolder.clearContext();
      }
    }

    chain.doFilter(request, response);
  }
}
