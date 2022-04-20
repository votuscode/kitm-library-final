package com.kitm.library.api.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertBookDto {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("pages")
  private Integer pages;

  @NotNull
  @Length(min = 13, max = 13, message = "ISBN must be 13 characters long")
  @JsonProperty("isbn")
  private String isbn;

  @JsonProperty("image")
  private String image;

  @NotNull
  @JsonProperty("categoryId")
  private UUID categoryId;

  @NotNull
  @JsonProperty("authorId")
  private UUID authorId;
}
