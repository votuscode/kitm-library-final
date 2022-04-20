package com.kitm.library.backend.domain.authentication;

import com.kitm.library.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 20.02.22
 */
@Service
@RequiredArgsConstructor
public class AuthenticationUserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s, not found", username)));
  }
}
