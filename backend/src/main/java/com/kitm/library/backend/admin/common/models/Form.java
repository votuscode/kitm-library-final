package com.kitm.library.backend.admin.common.models;

import com.kitm.library.backend.admin.common.interfaces.ISubmitFormDto;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
@AllArgsConstructor
public class Form {
  public String header;
  public String description;
  public String url;
  public String method;
  public List<Action> actions;

  @AllArgsConstructor
  public static class Action {
    public String label;
    public ISubmitFormDto.Action name;
    public String type;
  }

  public static Form add(String header, String description, String url) {
    return new Form(header, description, url, "POST", List.of(
        new Action("Add", ISubmitFormDto.Action.Add, "btn-primary")
    ));
  }

  public static Form update(String header, String description, String url) {
    return new Form(header, description, url, "POST", List.of(
        new Action("Update", ISubmitFormDto.Action.Update, "btn-warning"),
        new Action("Delete", ISubmitFormDto.Action.Delete, "btn-danger")
    ));
  }
}
