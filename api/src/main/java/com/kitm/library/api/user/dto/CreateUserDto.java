package com.kitm.library.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

  @NotNull
  @JsonProperty("name")
  String name;

  @NotNull
  @Email
  @JsonProperty("email")
  String email;

  @NotNull
  @JsonProperty("username")
  String username;

  @NotNull
  @JsonProperty("passwordOriginal")
  String passwordOriginal;

  @NotNull
  @JsonProperty("passwordConfirmation")
  String passwordConfirmation;

  @NotNull
  @JsonProperty("roles")
  Collection<String> roles;
}
