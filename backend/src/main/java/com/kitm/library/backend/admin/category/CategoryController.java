package com.kitm.library.backend.admin.category;

import com.kitm.library.backend.admin.category.dto.SubmitCategoryFormDto;
import com.kitm.library.backend.admin.common.models.Form;
import com.kitm.library.backend.admin.common.models.ItemList;
import com.kitm.library.backend.admin.common.models.Layout;
import com.kitm.library.backend.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * @version 1.0
 * @author votuscode (https://github.com/votuscode)
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/admin/categories")
  public String categoriesList(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Categories));

    model.addAttribute("itemList", new ItemList(
        "Categories",
        "Categories page",
        new ItemList.Action("Add category", "/admin/categories/add"),
        categoryService.findAll().stream()
            .map(category -> ItemList.Item.builder()
                .name(category.getName())
                .description(category.getDescription())
                .info(String.format("Books: %s", category.getBooks()))
                .href("/admin/categories/" + category.getId())
                .build())
            .toList()
    ));

    return "layout/item-list";
  }

  @PostMapping("/admin/categories/update")
  public String updateCategory(@ModelAttribute SubmitCategoryFormDto submitCategoryFormDto, Model model) {

    switch (submitCategoryFormDto.getAction()) {
      case Add -> categoryService.createOne(submitCategoryFormDto);
      case Update -> categoryService.updateOne(submitCategoryFormDto.getId(), submitCategoryFormDto);
      case Delete -> categoryService.deleteOne(submitCategoryFormDto.getId());
    }

    return "redirect:/admin/categories";
  }

  @GetMapping("/admin/categories/add")
  public String addCategory(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Categories));

    model.addAttribute("form", Form.add("Add category", "Add category page", "/admin/categories/update"));
    model.addAttribute("dto", SubmitCategoryFormDto.create());

    return "categories/update";
  }

  @GetMapping("/admin/categories/{id}")
  public String updateCategory(@PathVariable("id") UUID id, Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Categories));

    model.addAttribute("form", Form.update("Update category", "Update category page", "/admin/categories/update"));
    model.addAttribute("dto", SubmitCategoryFormDto.update(categoryService.getOne(id)));

    return "categories/update";
  }
}
