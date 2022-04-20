package com.kitm.library.backend.domain.book;

import com.kitm.library.api.book.IBookService;
import com.kitm.library.api.book.dto.BookDto;
import com.kitm.library.api.book.dto.UpsertBookDto;
import com.kitm.library.backend.domain.author.AuthorEntity;
import com.kitm.library.backend.domain.author.AuthorRepository;
import com.kitm.library.backend.domain.category.CategoryEntity;
import com.kitm.library.backend.domain.category.CategoryRepository;
import com.kitm.library.backend.domain.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BookService implements IBookService {

  private final BookRepository bookRepository;

  private final CategoryRepository categoryRepository;

  private final AuthorRepository authorRepository;

  @Override
  public Collection<BookDto> findAll() {

    return bookRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public BookDto getOne(UUID id) {

    final BookEntity bookEntity = bookRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find book"));

    return convert(bookEntity);
  }

  @Override
  public BookDto createOne(UpsertBookDto upsertBookDto) {

    final CategoryEntity categoryEntity = categoryRepository
        .findById(upsertBookDto.getCategoryId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find category"));

    final AuthorEntity authorEntity = authorRepository
        .findById(upsertBookDto.getAuthorId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    final BookEntity bookEntity = BookEntity.builder()
        .name(upsertBookDto.getName())
        .description(upsertBookDto.getDescription())
        .pages(upsertBookDto.getPages())
        .isbn(upsertBookDto.getIsbn())
        .image(upsertBookDto.getImage())
        .categoryEntity(categoryEntity)
        .authorEntity(authorEntity)
        .build();

    return convert(
        bookRepository.save(bookEntity)
    );
  }

  @Override
  public BookDto updateOne(UUID id, UpsertBookDto upsertBookDto) {

    final BookEntity bookEntity = bookRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find book"));

    final CategoryEntity categoryEntity = categoryRepository
        .findById(upsertBookDto.getCategoryId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find category"));

    final AuthorEntity authorEntity = authorRepository
        .findById(upsertBookDto.getAuthorId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    bookEntity.setName(upsertBookDto.getName());
    bookEntity.setDescription(upsertBookDto.getDescription());
    bookEntity.setPages(upsertBookDto.getPages());
    bookEntity.setIsbn(upsertBookDto.getIsbn());
    bookEntity.setImage(upsertBookDto.getImage());
    bookEntity.setCategoryEntity(categoryEntity);
    bookEntity.setAuthorEntity(authorEntity);

    return convert(
        bookRepository.save(bookEntity)
    );
  }

  @Override
  public void deleteOne(UUID id) {

    bookRepository.deleteById(id);
  }

  private BookDto convert(BookEntity bookEntity) {

    final UUID orderId = Optional.ofNullable(bookEntity.getOrderEntity())
        .map(OrderEntity::getId)
        .orElse(null);

    return BookDto.builder()
        .id(bookEntity.getId())
        .name(bookEntity.getName())
        .description(bookEntity.getDescription())
        .pages(bookEntity.getPages())
        .isbn(bookEntity.getIsbn())
        .image(bookEntity.getImage())
        .authorId(bookEntity.getAuthorId())
        .categoryId(bookEntity.getCategoryId())
        .orderId(orderId)
        .build();
  }
}
