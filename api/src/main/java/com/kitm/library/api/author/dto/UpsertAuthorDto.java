package com.kitm.library.api.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertAuthorDto {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("description")
  private String description;
}
