package com.kitm.library.api.authentication.dto;

import com.kitm.library.api.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
public class AuthenticatedDto {

  @NotNull
  private String token;

  @NotNull
  private Date expires;

  @NotNull
  private UserDto user;
}
