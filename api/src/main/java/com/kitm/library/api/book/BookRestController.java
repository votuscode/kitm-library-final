package com.kitm.library.api.book;

import com.kitm.library.api.book.dto.BookDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Api(value = "Book")
@RestController
@RequestMapping(path = "/api/books")
@RequiredArgsConstructor
public class BookRestController {

  private final IBookService bookService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<BookDto> getBooks() {

    return bookService.findAll().stream().toList();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public BookDto getBook(@PathVariable("id") UUID id) {

    return bookService.getOne(id);
  }
}
