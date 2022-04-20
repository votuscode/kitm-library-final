package com.kitm.library.backend.domain.user;

import com.kitm.library.api.user.IUserService;
import com.kitm.library.api.user.dto.CreateUserDto;
import com.kitm.library.api.user.dto.UserDto;
import com.kitm.library.backend.domain.role.RoleEntity;
import com.kitm.library.backend.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final RoleRepository roleRepository;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public Collection<UserDto> findAll() {
    return userRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public UserDto getOne(UUID userId) {

    final UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    return convert(userEntity);
  }

  @Transactional
  @Override
  public UserDto createOne(CreateUserDto createUserDto) {
    if (userRepository.findUserEntityByUsername(createUserDto.getUsername()).isPresent()) {
      throw new ValidationException("Username already registered.");
    }

    if (!createUserDto.getPasswordOriginal().equals(createUserDto.getPasswordConfirmation())) {
      throw new ValidationException("Password doesn't match.");
    }

    if (createUserDto.getRoles() == null) {
      throw new ValidationException("Roles are not provided.");
    }

    final Set<RoleEntity> roleEntities = createUserDto.getRoles().stream()
        .map(roleRepository::findRoleEntityByName)
        .collect(Collectors.toUnmodifiableSet());

    if (roleEntities.size() == 0) {
      throw new ValidationException("Invalid roles.");
    }

    final UserEntity userEntity = UserEntity.builder()
        .name(createUserDto.getName())
        .email(createUserDto.getEmail())
        .username(createUserDto.getUsername())
        .roles(roleEntities)
        .password(passwordEncoder.encode(createUserDto.getPasswordOriginal()))
        .build();

    return convert(userRepository.save(userEntity));
  }

  @Override
  public UserDto updateOne(UUID userId, CreateUserDto createUserDto) {

    final UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    final Collection<RoleEntity> roles = createUserDto.getRoles().stream()
        .map(roleName -> roleRepository.findRoleEntityByName(roleName))
        .toList();

    userEntity.setName(createUserDto.getName());
//    userEntity.setUsername(createUserDto.getUsername());
    userEntity.setEmail(createUserDto.getEmail());
    userEntity.setRoles(roles);
    userEntity.setPassword(createUserDto.getPasswordOriginal());

    return convert(
        userRepository.save(userEntity)
    );
  }

  @Override
  public void deleteOne(UUID userId) {

    final UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Could not find user"));

    userRepository.delete(userEntity);
  }

  @Override
  public UserDto getAuthenticatedUser(Authentication authentication) {

    final UserEntity userEntity = (UserEntity) authentication.getPrincipal();

    userRepository.findById(userEntity.getId())
        .orElseThrow(() -> new EntityNotFoundException("User not found"));

    return convert(userEntity);
  }

  @Override
  public boolean isUsernameExist(String username) {

    return userRepository.findUserEntityByUsername(username)
        .isPresent();
  }

  public UserDto convert(UserEntity userEntity) {

    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .username(userEntity.getUsername())
        .roles(userEntity.getRoles().stream()
            .map(RoleEntity::getName)
            .collect(Collectors.toUnmodifiableSet()))
        .createdAt(userEntity.getCreatedAt())
        .updatedAt(userEntity.getUpdatedAt())
        .build();
  }
}
