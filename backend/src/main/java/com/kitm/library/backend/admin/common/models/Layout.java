package com.kitm.library.backend.admin.common.models;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 15.04.22
 */
@AllArgsConstructor
public class Layout {
  public String home;
  public List<Item> items;

  @AllArgsConstructor
  public static class Item {
    public String label;
    public String href;
    public boolean active;
  }

  @AllArgsConstructor
  public enum Pages {
    Home("Home", "/admin"),
    Users("Edit users", "/admin/users"),
    Authors("Edit authors", "/admin/authors"),
    Categories("Edit categories", "/admin/categories"),
    Books("Edit books", "/admin/books");

    public String label;
    public String href;
  }

  public static Layout create(Pages active) {
    return new Layout("/admin", Arrays.stream(Pages.values())
        .map(page -> new Item(page.label, page.href, page.name().equals(active.name())))
        .toList()
    );
  }
}
