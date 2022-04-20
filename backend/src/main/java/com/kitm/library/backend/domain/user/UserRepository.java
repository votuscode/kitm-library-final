package com.kitm.library.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findUserEntityByUsername(String username);
}
