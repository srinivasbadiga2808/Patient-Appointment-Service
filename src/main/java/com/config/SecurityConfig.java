package com.example.hs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("adminpass")
            .roles("ADMIN")
            .build();

    UserDetails doctor = User.withDefaultPasswordEncoder()
            .username("doc")
            .password("docpass")
            .roles("DOCTOR")
            .build();

    UserDetails receptionist = User.withDefaultPasswordEncoder()
            .username("recept")
            .password("receppass")
            .roles("RECEPTIONIST")
            .build();

    return new InMemoryUserDetailsManager(admin, doctor, receptionist);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**").permitAll()
          .requestMatchers("/api/**").authenticated()
          .anyRequest().permitAll()
      )
      .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}
