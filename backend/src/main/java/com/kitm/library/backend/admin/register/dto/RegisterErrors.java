package com.kitm.library.backend.admin.register.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
@Data
@Builder
public class RegisterErrors {

  private String name;

  private String email;

  private String username;

  private String password;
}
