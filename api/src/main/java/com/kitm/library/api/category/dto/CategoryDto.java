package com.kitm.library.api.category.dto;

import com.kitm.library.api.common.IGenericDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Data
@Builder
public class CategoryDto implements IGenericDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer books;
}
