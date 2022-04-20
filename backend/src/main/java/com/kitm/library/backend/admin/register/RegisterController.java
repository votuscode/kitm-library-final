package com.kitm.library.backend.admin.register;

import com.google.common.collect.ImmutableList;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.backend.admin.register.dto.RegisterDto;
import com.kitm.library.backend.admin.register.dto.RegisterErrors;
import com.kitm.library.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class RegisterController {

  private final UserService userService;

  @GetMapping("/register")
  public String page(Model model) {

    model.addAttribute("Util", new Util());
    model.addAttribute("dto", RegisterDto.builder()
        .build());

    return "register";
  }

  @PostMapping("/register")
  public String register(@ModelAttribute RegisterDto registerDto, Model model) {

    final RegisterErrors registerErrors = validate(registerDto);

    if (registerErrors == null) {
      userService.createOne(CreateUserDto.builder()
          .roles(ImmutableList.of("USER"))
          .name(registerDto.getName())
          .email(registerDto.getEmail())
          .username(registerDto.getUsername())
          .passwordOriginal(registerDto.getPassword())
          .passwordConfirmation(registerDto.getConfirmPassword())
          .build());

      return "login";
    }

    model.addAttribute("Util", new Util());
    model.addAttribute("registerErrors", registerErrors);
    model.addAttribute("registerDto", registerDto);

    return "register";
  }

  private RegisterErrors validate(RegisterDto registerDto) {

    if (userService.isUsernameExist(registerDto.getUsername())) {
      return RegisterErrors.builder()
          .username("This username is already taken.")
          .build();
    }

    if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
      return RegisterErrors.builder()
          .password("Password does not match.")
          .build();
    }

    return null;
  }

  public static class Util {

    public String className(RegisterErrors registerErrors, String name) {

      if (registerErrors == null) {
        return "";
      }

      switch (name) {
        case "name":
          return className(registerErrors.getName());
        case "username":
          return className(registerErrors.getUsername());
        case "email":
          return className(registerErrors.getEmail());
        case "password":
          return className(registerErrors.getPassword());
      }

      return "";
    }

    private String className(String value) {

      return value == null ? "is-valid" : "is-invalid";
    }
  }
}
