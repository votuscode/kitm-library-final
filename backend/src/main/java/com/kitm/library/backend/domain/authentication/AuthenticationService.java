package com.kitm.library.backend.domain.authentication;

import com.kitm.library.api.authentication.IAuthenticationService;
import com.kitm.library.api.authentication.dto.AuthenticatedDto;
import com.kitm.library.api.authentication.dto.LoginDto;
import com.kitm.library.backend.domain.user.UserEntity;
import com.kitm.library.backend.domain.user.UserService;
import com.kitm.library.backend.spring.web.config.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtil jwtTokenUtil;

  private final UserService userService;

  @Override
  public AuthenticatedDto login(LoginDto loginDto) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

    return convert(authentication);
  }

  @Override
  public AuthenticatedDto convert(Authentication authentication) {

    UserEntity userEntity = (UserEntity) authentication.getPrincipal();

    String token = jwtTokenUtil.generateAccessToken(userEntity);
    Date expires = jwtTokenUtil.getExpirationDate(token);

    return AuthenticatedDto.builder()
        .token(token)
        .expires(expires)
        .user(userService.convert(userEntity))
        .build();
  }
}
