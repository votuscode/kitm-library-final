package com.kitm.library.api.role;

import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.role.dto.RoleDto;

import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 14.02.22
 */
public interface IRoleService {

  Collection<RoleDto> findAll();

  RoleDto createOne(CreateRoleDto createRoleDto);
}
