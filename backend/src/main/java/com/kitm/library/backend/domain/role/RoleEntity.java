package com.kitm.library.backend.domain.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 23.09.21
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class RoleEntity implements GrantedAuthority {
  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false, unique = true, length = 8)
  private String name;

  @Override
  public String getAuthority() {
    return name;
  }
}
