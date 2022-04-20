package com.kitm.library.backend.domain.author;

import com.kitm.library.api.author.IAuthorService;
import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.UpsertAuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Collection<AuthorDto> findAll() {

    return authorRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public AuthorDto getOne(UUID id) {

    final AuthorEntity authorEntity = authorRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    return convert(authorEntity);
  }

  @Override
  public AuthorDto createOne(UpsertAuthorDto upsertAuthorDto) {

    final AuthorEntity authorEntity = AuthorEntity.builder()
        .name(upsertAuthorDto.getName())
        .description(upsertAuthorDto.getDescription())
        .build();

    return convert(
        authorRepository.save(authorEntity)
    );
  }

  @Override
  public AuthorDto updateOne(UUID id, UpsertAuthorDto upsertAuthorDto) {

    final AuthorEntity authorEntity = authorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    authorEntity.setName(upsertAuthorDto.getName());
    authorEntity.setDescription(upsertAuthorDto.getDescription());

    return convert(
        authorRepository.save(authorEntity)
    );
  }

  @Override
  public void deleteOne(UUID id) {

    authorRepository.deleteById(id);
  }

  public AuthorDto convert(AuthorEntity authorEntity) {

    final Integer books = Optional.ofNullable(authorEntity.getBookEntitySet())
        .map(Set::size)
        .orElse(0);

    return AuthorDto.builder()
        .id(authorEntity.getId())
        .name(authorEntity.getName())
        .description(authorEntity.getDescription())
        .books(books)
        .build();
  }
}
