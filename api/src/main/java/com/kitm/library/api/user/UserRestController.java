package com.kitm.library.api.user;

import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
@Api(value = "User")
@RestController()
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserRestController {

  private final IUserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<UserDto> getUsers() {
    return userService.findAll().stream().toList();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public UserDto createUser(@RequestBody @Valid CreateUserDto createUserDto) {
    return userService.createOne(createUserDto);
  }
}
