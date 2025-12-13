package nl.foo.bar.demo.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;
  private final JwtUserDetailsService jwtUserDetailsService;

  public AuthenticationController(
    AuthenticationManager authenticationManager,
    JwtTokenService jwtTokenService,
    JwtUserDetailsService jwtUserDetailsService
  ) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenService = jwtTokenService;
    this.jwtUserDetailsService = jwtUserDetailsService;
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(
    @RequestBody @Valid final AuthenticationRequest authenticationRequest
  ) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      ));
    } catch (BadCredentialsException e) {
      throw new ResponseStatusException(
        HttpStatus.UNAUTHORIZED,
        "Incorrect username or password",
        e
      );
    }

    UserDetails userDetails
      = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));

    return authenticationResponse;
  }

}