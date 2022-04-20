package com.kitm.library.backend.domain.category;

import com.kitm.library.api.category.ICategoryService;
import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.UpsertCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Collection<CategoryDto> findAll() {

    return categoryRepository.findAll().stream()
        .map(this::convert)
        .toList();
  }

  @Override
  public CategoryDto getOne(UUID id) {

    final CategoryEntity categoryEntity = categoryRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find category"));

    return convert(categoryEntity);
  }

  @Override
  public CategoryDto createOne(UpsertCategoryDto upsertCategoryDto) {

    final CategoryEntity categoryEntity = CategoryEntity.builder()
        .name(upsertCategoryDto.getName())
        .description(upsertCategoryDto.getDescription())
        .build();

    return convert(
        categoryRepository.save(categoryEntity)
    );
  }

  @Override
  public CategoryDto updateOne(UUID id, UpsertCategoryDto upsertCategoryDto) {

    final CategoryEntity categoryEntity = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find author"));

    categoryEntity.setName(upsertCategoryDto.getName());
    categoryEntity.setDescription(upsertCategoryDto.getDescription());

    return convert(
        categoryRepository.save(categoryEntity)
    );
  }

  @Override
  public void deleteOne(UUID id) {

    categoryRepository.deleteById(id);
  }

  public CategoryDto convert(CategoryEntity categoryEntity) {

    final Integer books = Optional.ofNullable(categoryEntity.getBookEntitySet())
        .map(Set::size)
        .orElse(0);

    return CategoryDto.builder()
        .id(categoryEntity.getId())
        .name(categoryEntity.getName())
        .description(categoryEntity.getDescription())
        .books(books)
        .build();
  }
}
