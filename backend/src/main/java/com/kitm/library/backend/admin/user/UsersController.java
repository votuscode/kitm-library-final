package com.kitm.library.backend.admin.user;

import com.kitm.library.backend.admin.common.models.Form;
import com.kitm.library.backend.admin.common.models.ItemList;
import com.kitm.library.backend.admin.common.models.Layout;
import com.kitm.library.backend.admin.common.models.SelectOptions;
import com.kitm.library.backend.admin.user.dto.SubmitUserFormDto;
import com.kitm.library.backend.domain.role.RoleService;
import com.kitm.library.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class UsersController {

  private final RoleService roleService;

  private final UserService userService;

  @GetMapping("/admin/users")
  public String usersList(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Users));

    model.addAttribute("itemList", new ItemList(
        "Users",
        "Users page",
        new ItemList.Action("Add user", "/admin/users/add"),
        userService.findAll().stream()
            .map(user -> ItemList.Item.builder()
                .name(user.getName())
                .description(user.getUsername())
                .info(String.format("E-mail: %s", user.getEmail()))
                .badge(user.getRoles().stream().collect(Collectors.joining(", ")))
                .href("/admin/users/" + user.getId())
                .build())
            .toList()
    ));

    return "layout/item-list";
  }

  @PostMapping("/admin/users/update")
  public String updateUser(@ModelAttribute SubmitUserFormDto submitUserFormDto, Model model) {

    switch (submitUserFormDto.getAction()) {
      case Add -> userService.createOne(submitUserFormDto);
      case Update -> userService.updateOne(submitUserFormDto.getId(), submitUserFormDto);
      case Delete -> userService.deleteOne(submitUserFormDto.getId());
    }

    return "redirect:/admin/users";
  }

  @GetMapping("/admin/users/add")
  public String addUser(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Users));

    model.addAttribute("form", Form.add("Add user", "Add user page", "/admin/users/update"));
    model.addAttribute("dto", SubmitUserFormDto.create());
    model.addAttribute("roles", SelectOptions.from(roleService.findAll()));

    return "users/update";
  }

  @GetMapping("/admin/users/{id}")
  public String updateUser(@PathVariable("id") UUID id, Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Users));

    model.addAttribute("form", Form.update("Update user", "Update user page", "/admin/users/update"));
    model.addAttribute("dto", SubmitUserFormDto.update(userService.getOne(id)));
    model.addAttribute("roles", SelectOptions.from(roleService.findAll()));

    return "users/update";
  }
}
