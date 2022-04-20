package com.kitm.library.backend.admin.author.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.UpsertAuthorDto;
import com.kitm.library.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SubmitAuthorFormDto extends UpsertAuthorDto implements ISubmitFormDto {

  @JsonProperty("id")
  private UUID id;

  @NotNull
  @JsonProperty("action")
  private Action action;

  private SubmitAuthorFormDto(String name, String description, UUID id) {
    super(name, description);
    this.id = id;
  }

  public static SubmitAuthorFormDto create() {
    return new SubmitAuthorFormDto();
  }

  public static SubmitAuthorFormDto update(AuthorDto authorDto) {
    return new SubmitAuthorFormDto(authorDto.getName(), authorDto.getDescription(), authorDto.getId());
  }
}
