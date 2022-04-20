package com.kitm.library.api.user;

import com.kitm.library.api.common.ICrudService;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
public interface IUserService extends ICrudService<UserDto, CreateUserDto> {

  UserDto getAuthenticatedUser(Authentication authentication);

  boolean isUsernameExist(String username);
}
