package com.kitm.library.backend.admin.register.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

  @JsonProperty("name")
  @NotNull
  public String name;

  @JsonProperty("email")
  @NotNull
  @Email
  public String email;

  @JsonProperty("username")
  @NotNull
  public String username;

  @JsonProperty("password")
  @NotNull
  public String password;

  @JsonProperty("confirmPassword")
  @NotNull
  public String confirmPassword;
}
