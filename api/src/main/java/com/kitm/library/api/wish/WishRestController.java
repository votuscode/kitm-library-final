package com.kitm.library.api.wish;

import com.kitm.library.api.user.IUserService;
import com.kitm.library.api.user.dto.UserDto;
import com.kitm.library.api.wish.dto.CreateWishDto;
import com.kitm.library.api.wish.dto.WishDto;
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
@Api(value = "Wish")
@RestController
@RequestMapping(path = "/api/wishes")
@RequiredArgsConstructor
public class WishRestController {

  private final IWishService wishService;

  private final IUserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<WishDto> getWishes(Authentication authentication) {

    final UserDto userDto = userService.getAuthenticatedUser(authentication);

    return wishService.findAllByUserId(userDto.getId());
  }

  @GetMapping(value = "/{wishId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WishDto getWish(@PathVariable("wishId") UUID wishId) {

    return wishService.getOne(wishId);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public WishDto createWish(@RequestBody @Valid CreateWishDto createWishDto) {

    return wishService.createOne(createWishDto);
  }

  // TODO: @DeleteMapping produces incorrect Swagger definitions
  @PostMapping(value = "/{wishId}")
  public void deleteWish(@PathVariable("wishId") UUID wishId) {

    wishService.deleteOne(wishId);
  }
}
