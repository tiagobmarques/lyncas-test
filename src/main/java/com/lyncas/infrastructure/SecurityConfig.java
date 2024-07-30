package com.lyncas.infrastructure;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(jwtSecret, userDetailsService());

    http
        .authorizeRequests()
        .antMatchers("/auth/login").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/v2/api-docs").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/contasapagar/**").authenticated()
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    User.UserBuilder users = User.withUsername("tiago")
        .password(passwordEncoder().encode("password123"))
        .roles("USER");
    return new InMemoryUserDetailsManager(users.build());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
