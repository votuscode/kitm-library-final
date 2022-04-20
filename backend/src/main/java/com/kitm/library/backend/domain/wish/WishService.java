package com.kitm.library.backend.domain.wish;

import com.kitm.library.api.wish.IWishService;
import com.kitm.library.api.wish.dto.CreateWishDto;
import com.kitm.library.api.wish.dto.WishDto;
import com.kitm.library.backend.domain.book.BookEntity;
import com.kitm.library.backend.domain.book.BookRepository;
import com.kitm.library.backend.domain.user.UserEntity;
import com.kitm.library.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class WishService implements IWishService {

  private final WishRepository wishRepository;

  private final BookRepository bookRepository;

  private final UserRepository userRepository;

  @Override
  public Collection<WishDto> findAllByUserId(UUID userId) {

    userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    return wishRepository.findAll().stream()
        .filter(wishEntity -> wishEntity.getUserEntity().getId().equals(userId))
        .map(this::convert)
        .toList();
  }

  @Override
  public WishDto getOne(UUID wishId) {

    final WishEntity wishEntity = wishRepository.findById(wishId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find wish"));

    return convert(wishEntity);
  }

  @Override
  public WishDto createOne(CreateWishDto createWishDto) {

    final UserEntity userEntity = userRepository.findById(createWishDto.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    final BookEntity bookEntity = bookRepository.findById(createWishDto.getBookId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find book"));

    final WishEntity wishEntity = wishRepository.save(WishEntity.builder()
        .userEntity(userEntity)
        .bookEntity(bookEntity)
        .build());

    return convert(wishEntity);
  }

  @Override
  public void deleteOne(UUID wishId) {

    final WishEntity wishEntity = wishRepository.findById(wishId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find wish"));

    wishRepository.delete(wishEntity);
  }

  private WishDto convert(WishEntity wishEntity) {

    return WishDto.builder()
        .id(wishEntity.getId())
        .bookId(wishEntity.getBookEntity().getId())
        .userId(wishEntity.getUserEntity().getId())
        .build();
  }
}
