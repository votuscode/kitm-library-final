package com.kitm.library.backend.domain.order;

import com.kitm.library.api.order.IOrderService;
import com.kitm.library.api.order.dto.CreateOrderDto;
import com.kitm.library.api.order.dto.OrderDto;
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
public class OrderService implements IOrderService {

  private final OrderRepository orderRepository;

  private final BookRepository bookRepository;

  private final UserRepository userRepository;

  @Override
  public Collection<OrderDto> findAllByUserId(UUID userId) {

    userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    return orderRepository.findAll().stream()
        .filter(orderEntity -> orderEntity.getUserEntity().getId().equals(userId))
        .map(this::convert)
        .toList();
  }

  @Override
  public OrderDto getOne(UUID orderId) {

    final OrderEntity orderEntity = orderRepository.findById(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find order"));

    return convert(orderEntity);
  }

  @Override
  public OrderDto createOne(CreateOrderDto createOrderDto) {

    final UserEntity userEntity = userRepository.findById(createOrderDto.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    final BookEntity bookEntity = bookRepository.findById(createOrderDto.getBookId())
        .orElseThrow(() -> new EntityNotFoundException("Could not find book"));

    final OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
        .userEntity(userEntity)
        .bookEntity(bookEntity)
        .build());

    return convert(orderEntity);
  }

  @Override
  public void deleteOne(UUID orderId) {

    final OrderEntity orderEntity = orderRepository.findById(orderId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find order"));

    orderRepository.delete(orderEntity);
  }

  private OrderDto convert(OrderEntity orderEntity) {

    return OrderDto.builder()
        .id(orderEntity.getId())
        .bookId(orderEntity.getBookEntity().getId())
        .userId(orderEntity.getUserEntity().getId())
        .build();
  }
}
