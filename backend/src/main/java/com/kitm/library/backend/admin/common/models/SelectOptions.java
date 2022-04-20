package com.kitm.library.backend.admin.common.models;

import com.kitm.library.api.common.IGenericDto;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 16.04.22
 */
@AllArgsConstructor
public class SelectOptions {

  public List<Option> options;

  @AllArgsConstructor
  public static class Option {
    public UUID id;
    public String label;
  }

  public static <T extends IGenericDto> SelectOptions from(Collection<T> genericEntities) {
    return new SelectOptions(genericEntities.stream()
        .map(genericEntity -> new Option(genericEntity.getId(), genericEntity.getName()))
        .toList());
  }
}
