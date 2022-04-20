package com.kitm.library.backend.admin.book.dto;

import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.category.dto.CategoryDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 16.04.22
 */
@Data
@Builder
public class BookDetailDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;

  private String description;

  private Integer pages;

  private String isbn;

  private String image;

  @NotNull
  private AuthorDto author;

  @NotNull
  private CategoryDto category;
}
