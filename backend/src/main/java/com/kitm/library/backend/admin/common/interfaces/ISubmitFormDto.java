package com.kitm.library.backend.admin.common.interfaces;

import lombok.AllArgsConstructor;

import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
public interface ISubmitFormDto {

  UUID getId();

  Action getAction();

  @AllArgsConstructor
  enum Action {
    Add("add"),
    Update("update"),
    Delete("delete");

    public String name;
  }
}
