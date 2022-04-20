package com.kitm.library.api.role.dto;

import com.kitm.library.api.common.IGenericDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 12.02.22
 */
@Data
@Builder
public class RoleDto implements IGenericDto {

  @NotNull
  private UUID id;

  @NotNull
  private String name;
}
