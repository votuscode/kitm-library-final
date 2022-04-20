package com.kitm.library.backend.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
  RoleEntity findRoleEntityByName(String name);
}
