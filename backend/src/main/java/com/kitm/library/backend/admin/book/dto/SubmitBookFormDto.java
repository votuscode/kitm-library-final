package com.kitm.library.backend.admin.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kitm.library.api.book.dto.BookDto;
import com.kitm.library.api.book.dto.UpsertBookDto;
import com.kitm.library.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 16.04.22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SubmitBookFormDto extends UpsertBookDto implements ISubmitFormDto {

  @JsonProperty("id")
  private UUID id;

  @NotNull
  @JsonProperty("action")
  private Action action;

  private SubmitBookFormDto(String name, String description, Integer pages, String isbn, String image, UUID categoryId, UUID authorId, UUID id) {
    super(name, description, pages, isbn, image, categoryId, authorId);
    this.id = id;
  }

  public static SubmitBookFormDto create() {
    return new SubmitBookFormDto();
  }

  public static SubmitBookFormDto update(BookDto bookDto) {
    return new SubmitBookFormDto(
        bookDto.getName(),
        bookDto.getDescription(),
        bookDto.getPages(),
        bookDto.getIsbn(),
        bookDto.getImage(),
        bookDto.getCategoryId(),
        bookDto.getAuthorId(),
        bookDto.getId()
    );
  }
}
