package nl.foo.bar.demo.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class JwtSecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration authenticationConfiguration
  ) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  public JwtRequestFilter jwtRequestFilter(
    JwtUserDetailsService jwtUserDetailsService,
    JwtTokenService jwtTokenService
  ) {
    return new JwtRequestFilter(jwtTokenService, jwtUserDetailsService);
  }

  @Bean
  public SecurityFilterChain configure(
    final HttpSecurity http,
    final JwtUserDetailsService jwtUserDetailsService,
    final JwtTokenService jwtTokenService
  ) throws Exception {
    return http.cors(withDefaults())
      .csrf((csrf) -> csrf.disable())
      .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/", "/authenticate")
        .permitAll()
        .anyRequest()
        .fullyAuthenticated())
      .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(
        jwtRequestFilter(jwtUserDetailsService, jwtTokenService),
        UsernamePasswordAuthenticationFilter.class
      )
      .build();
  }

}