package com.vvs.springrxpostgresqlapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {

    UserDetails user = User
      .withUsername("user")
      .password(passwordEncoder().encode("password"))
      .roles("USER")
      .build();

    UserDetails admin = User
      .withUsername("admin")
      .password(passwordEncoder().encode("Password1"))
      .roles("ADMIN")
      .build();

    return new MapReactiveUserDetailsService(user, admin);
  }

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
      .cors().configurationSource(createCorsConfigurationSource()).and()
      .exceptionHandling()
      .authenticationEntryPoint((shs, e) -> Mono.fromRunnable(() -> {
        shs.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      })).accessDeniedHandler((shs, e) -> Mono.fromRunnable(() -> {
        shs.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
      })).and()
      .csrf().disable()
      .formLogin().disable()
      .authorizeExchange()
      .pathMatchers(HttpMethod.POST).hasAuthority("ROLE_ADMIN")
      .pathMatchers(HttpMethod.PUT).hasAuthority("ROLE_ADMIN")
      .pathMatchers(HttpMethod.DELETE).hasAuthority("ROLE_ADMIN")
      .pathMatchers("/api/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_ADMIN")
      .anyExchange().authenticated().and()
      .httpBasic().and()
      .build();
  }

  public CorsConfigurationSource createCorsConfigurationSource() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    source.registerCorsConfiguration("**", config);

    return source;
  }

}
