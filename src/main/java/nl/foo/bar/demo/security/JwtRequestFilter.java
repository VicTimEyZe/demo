package nl.foo.bar.demo.security;

import java.io.IOException;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtRequestFilter extends OncePerRequestFilter {

  private final JwtTokenService jwtTokenService;
  private final JwtUserDetailsService jwtUserDetailsService;

  public JwtRequestFilter(
    JwtTokenService jwtTokenService,
    JwtUserDetailsService jwtUserDetailsService
  ) {
    this.jwtTokenService = jwtTokenService;
    this.jwtUserDetailsService = jwtUserDetailsService;
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  ) throws ServletException, IOException {

    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    String token = header.substring(7);
    DecodedJWT jwt = jwtTokenService.validateToken(token);
    if (jwt == null || jwt.getSubject() == null) {
      // validation failed or token expired
      chain.doFilter(request, response);
      return;
    }

    UserDetails userDetails;
    try {
      userDetails = jwtUserDetailsService.loadUserByUsername(jwt.getSubject());
    } catch (final UsernameNotFoundException userNotFoundEx) {
      // user not found
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(userDetails,
      null,
      userDetails.getAuthorities()
    );
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(request, response);
  }

}
