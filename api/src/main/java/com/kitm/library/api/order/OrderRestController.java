package com.kitm.library.api.order;

import com.kitm.library.api.order.dto.CreateOrderDto;
import com.kitm.library.api.order.dto.OrderDto;
import com.kitm.library.api.user.IUserService;
import com.kitm.library.api.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Api(value = "Order")
@RestController
@RequestMapping(path = "/api/orders")
@RequiredArgsConstructor
public class OrderRestController {

  private final IOrderService orderService;

  private final IUserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<OrderDto> getOrders(Authentication authentication) {

    final UserDto userDto = userService.getAuthenticatedUser(authentication);

    return orderService.findAllByUserId(userDto.getId());
  }

  @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public OrderDto getOrder(@PathVariable("orderId") UUID orderId) {

    return orderService.getOne(orderId);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public OrderDto createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {

    return orderService.createOne(createOrderDto);
  }

  // TODO: @DeleteMapping produces incorrect Swagger definitions
  @PostMapping(value = "/{orderId}")
  public void deleteOrder(@PathVariable("orderId") UUID orderId) {

    orderService.deleteOne(orderId);
  }
}
