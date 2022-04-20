package com.kitm.library.api.category;

import com.kitm.library.api.category.dto.CategoryDto;
import com.kitm.library.api.category.dto.UpsertCategoryDto;
import com.kitm.library.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface ICategoryService extends ICrudService<CategoryDto, UpsertCategoryDto> {
}
