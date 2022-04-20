package com.kitm.library.backend.admin.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.UpsertCategoryDto;
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
public class SubmitCategoryFormDto extends UpsertCategoryDto implements ISubmitFormDto {

  @JsonProperty("id")
  private UUID id;

  @NotNull
  @JsonProperty("action")
  private Action action;

  private SubmitCategoryFormDto(String name, String description, UUID id) {
    super(name, description);
    this.id = id;
  }

  public static SubmitCategoryFormDto create() {
    return new SubmitCategoryFormDto();
  }

  public static SubmitCategoryFormDto update(CategoryDto categoryDto) {
    return new SubmitCategoryFormDto(categoryDto.getName(), categoryDto.getDescription(), categoryDto.getId());
  }
}
