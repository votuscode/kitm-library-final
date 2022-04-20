package com.kitm.library.backend.admin.book;

import com.kitm.library.backend.admin.book.dto.SubmitBookFormDto;
import com.kitm.library.backend.admin.common.models.Form;
import com.kitm.library.backend.admin.common.models.ItemList;
import com.kitm.library.backend.admin.common.models.Layout;
import com.kitm.library.backend.admin.common.models.SelectOptions;
import com.kitm.library.backend.domain.author.AuthorService;
import com.kitm.library.backend.domain.book.BookDetailService;
import com.kitm.library.backend.domain.book.BookService;
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
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 19.02.22
 */
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  private final BookDetailService bookDetailService;

  private final AuthorService authorService;

  private final CategoryService categoryService;

  @GetMapping("/admin/books")
  public String categoriesList(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Books));

    model.addAttribute("itemList", new ItemList(
        "Books",
        "Books page",
        new ItemList.Action("Add book", "/admin/books/add"),
        bookDetailService.findAll().stream()
            .map(book -> ItemList.Item.builder()
                .image(book.getImage())
                .name(book.getName())
                .description(book.getDescription())
                .info(String.format("Author: %s, category: %s", book.getAuthor().getName(), book.getCategory().getName()))
                .href("/admin/books/" + book.getId())
                .build())
            .toList()
    ));

    return "layout/item-list";
  }

  @PostMapping("/admin/books/update")
  public String updateBook(@ModelAttribute SubmitBookFormDto submitBookFormDto, Model model) {

    switch (submitBookFormDto.getAction()) {
      case Add -> bookService.createOne(submitBookFormDto);
      case Update -> bookService.updateOne(submitBookFormDto.getId(), submitBookFormDto);
      case Delete -> bookService.deleteOne(submitBookFormDto.getId());
    }

    return "redirect:/admin/books";
  }

  @GetMapping("/admin/books/add")
  public String addBook(Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Books));

    model.addAttribute("form", Form.add("Add book", "Add book page", "/admin/books/update"));
    model.addAttribute("dto", SubmitBookFormDto.create());
    model.addAttribute("categories", SelectOptions.from(categoryService.findAll()));
    model.addAttribute("authors", SelectOptions.from(authorService.findAll()));

    return "books/update";
  }

  @GetMapping("/admin/books/{id}")
  public String updateBook(@PathVariable("id") UUID id, Model model) {

    model.addAttribute("layout", Layout.create(Layout.Pages.Books));

    model.addAttribute("form", Form.update("Update book", "Update book page", "/admin/books/update"));
    model.addAttribute("dto", SubmitBookFormDto.update(bookService.getOne(id)));
    model.addAttribute("categories", SelectOptions.from(categoryService.findAll()));
    model.addAttribute("authors", SelectOptions.from(authorService.findAll()));

    return "books/update";
  }
}
