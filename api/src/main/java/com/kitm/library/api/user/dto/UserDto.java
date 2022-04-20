package com.kitm.library.api.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Data
@Builder
public class UserDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;
  @NotNull
  private String email;
  @NotNull
  private String username;

  @NotNull
  private Set<String> roles;

  @NotNull
  private Date createdAt;
  @NotNull
  private Date updatedAt;
}
