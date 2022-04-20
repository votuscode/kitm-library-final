package com.kitm.library.api.role;

import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.role.dto.RoleDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@Api(value = "Role")
@RestController
@RequestMapping(path = "/api/roles")
@RequiredArgsConstructor
public class RoleRestController {

  private final IRoleService roleService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<RoleDto> getRoles() {
    return roleService.findAll().stream().toList();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public RoleDto createRole(@RequestBody @Valid CreateRoleDto createRoleDto) {
    return roleService.createOne(createRoleDto);
  }
}
