package com.kitm.library.backend.admin.role;

import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.backend.domain.role.RoleService;
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
public class RolesController {

  private final RoleService roleService;

  @GetMapping("/admin/roles")
  public String rolesList(Model model) {

    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("createRoleDto", new CreateRoleDto());

    return "roles";
  }

  @PostMapping("/admin/roles")
  public String addRole(@ModelAttribute CreateRoleDto createRoleDto, Model model) {

    roleService.createOne(createRoleDto);

    model.addAttribute("roles", roleService.findAll());
    model.addAttribute("createRoleDto", new CreateRoleDto());

    return "roles";
  }
}
