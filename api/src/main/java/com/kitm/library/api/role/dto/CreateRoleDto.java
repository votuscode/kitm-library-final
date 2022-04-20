package com.kitm.library.api.role.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {

  @NotNull
  @JsonProperty("name")
  private String name;
}
