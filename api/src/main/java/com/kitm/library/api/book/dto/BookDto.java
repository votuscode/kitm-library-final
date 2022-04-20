package com.kitm.library.api.book.dto;

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
public class BookDto implements IGenericDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  private String description;

  private Integer pages;

  private String isbn;

  private String image;

  @NotNull
  private UUID authorId;

  @NotNull
  private UUID categoryId;

  // optional
  private UUID orderId;
}
