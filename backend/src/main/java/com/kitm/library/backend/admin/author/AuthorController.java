package com.kitm.library.backend.admin.author;

import com.kitm.library.backend.admin.author.dto.SubmitAuthorFormDto;
import com.kitm.library.backend.admin.common.models.Form;
import com.kitm.library.backend.admin.common.models.ItemList;
import com.kitm.library.backend.admin.common.models.Layout;
import com.kitm.library.backend.domain.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @GetMapping("/admin/authors")
  public String authorsList(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("itemList", new ItemList(
        "Authors",
        "Authors page",
        new ItemList.Action("Add author", "/admin/authors/add"),
        authorService.findAll().stream()
            .map(author -> ItemList.Item.builder()
                .name(author.getName())
                .description(author.getDescription())
                .info(String.format("Books: %s", author.getBooks()))
                .href("/admin/authors/" + author.getId())
                .build())
            .toList()
    ));

    return "layout/item-list";
  }

  @PostMapping("/admin/authors/update")
  public String updateAuthor(@ModelAttribute SubmitAuthorFormDto submitAuthorFormDto, Model model) {

    switch (submitAuthorFormDto.getAction()) {
      case Add -> authorService.createOne(submitAuthorFormDto);
      case Update -> authorService.updateOne(submitAuthorFormDto.getId(), submitAuthorFormDto);
      case Delete -> authorService.deleteOne(submitAuthorFormDto.getId());
    }

    return "redirect:/admin/authors";
  }

  @GetMapping("/admin/authors/add")
  public String addAuthor(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("form", Form.add("Add author", "Add author page", "/admin/authors/update"));
    model.addAttribute("dto", SubmitAuthorFormDto.create());

    return "authors/update";
  }

  @GetMapping("/admin/authors/{id}")
  public String updateAuthor(@PathVariable("id") UUID id, Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Authors));

    model.addAttribute("form", Form.update("Update author", "Update author page", "/admin/authors/update"));
    model.addAttribute("dto", SubmitAuthorFormDto.update(authorService.getOne(id)));

    return "authors/update";
  }
}
