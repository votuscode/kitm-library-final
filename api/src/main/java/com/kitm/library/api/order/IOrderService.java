package com.kitm.library.api.order;

import com.kitm.library.api.order.dto.CreateOrderDto;
import com.kitm.library.api.order.dto.OrderDto;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
public interface IOrderService {

  Collection<OrderDto> findAllByUserId(UUID userId);

  OrderDto getOne(UUID orderId);

  OrderDto createOne(CreateOrderDto createOrderDto);

  void deleteOne(UUID orderId);
}
