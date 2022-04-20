package com.kitm.library.backend.domain.book;

import com.kitm.library.backend.admin.book.dto.BookDetailDto;
import com.kitm.library.backend.domain.author.AuthorService;
import com.kitm.library.backend.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 16.04.22
 */
@Service
@RequiredArgsConstructor
public class BookDetailService {

  private final BookRepository bookRepository;

  private final AuthorService authorService;

  private final CategoryService categoryService;

  public Collection<BookDetailDto> findAll() {

    return bookRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }
  private BookDetailDto convert(BookEntity bookEntity) {

    return BookDetailDto.builder()
        .id(bookEntity.getId())
        .name(bookEntity.getName())
        .description(bookEntity.getDescription())
        .isbn(bookEntity.getIsbn())
        .image(bookEntity.getImage())
        .author(authorService.convert(bookEntity.getAuthorEntity()))
        .category(categoryService.convert(bookEntity.getCategoryEntity()))
        .build();
  }
}
