package com.kitm.library.backend.admin.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;
import com.kitm.library.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.04.22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SubmitUserFormDto extends CreateUserDto implements ISubmitFormDto {

  @JsonProperty("id")
  private UUID id;

  @NotNull
  @JsonProperty("action")
  private Action action;

  private SubmitUserFormDto(
      String name,
      String email,
      String username,
      String passwordOriginal,
      String passwordConfirmation,
      Collection<String> roles,
      UUID id
  ) {
    super(name, email, username, passwordOriginal, passwordConfirmation, roles);
    this.id = id;
  }

  public static SubmitUserFormDto create() {
    return new SubmitUserFormDto();
  }

  public static SubmitUserFormDto update(UserDto userDto) {
    return new SubmitUserFormDto(
        userDto.getName(),
        userDto.getEmail(),
        userDto.getUsername(),
        "",
        "",
        userDto.getRoles(),
        userDto.getId()
    );
  }
}
