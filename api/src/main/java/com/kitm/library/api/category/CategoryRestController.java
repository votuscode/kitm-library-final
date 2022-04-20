package com.kitm.library.api.category;

import com.kitm.library.api.category.dto.CategoryDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Api(value = "Category")
@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {

  private final ICategoryService categoryService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<CategoryDto> getCategories() {

    return categoryService.findAll().stream().toList();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public CategoryDto getCategory(@PathVariable("id") UUID id) {

    return categoryService.getOne(id);
  }
}
