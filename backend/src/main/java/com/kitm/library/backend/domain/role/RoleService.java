package com.kitm.library.backend.domain.role;

import com.kitm.library.api.role.IRoleService;
import com.kitm.library.api.role.dto.CreateRoleDto;
import com.kitm.library.api.role.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 24.09.21
 */
@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements IRoleService {

  private final RoleRepository roleRepository;

  @Override
  public Collection<RoleDto> findAll() {
    return roleRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public RoleDto createOne(CreateRoleDto createRoleDto) {
    final RoleEntity roleEntity = RoleEntity.builder()
        .name(createRoleDto.getName())
        .build();

    return convert(
        roleRepository.save(roleEntity)
    );
  }

  private RoleDto convert(RoleEntity roleEntity) {
    return RoleDto.builder()
        .id(roleEntity.getId())
        .name(roleEntity.getName())
        .build();
  }
}
