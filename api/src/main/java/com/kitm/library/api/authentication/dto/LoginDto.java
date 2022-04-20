package com.kitm.library.api.authentication.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
public class LoginDto {

  @NotNull
  private String username;

  @NotNull
  private String password;
}
