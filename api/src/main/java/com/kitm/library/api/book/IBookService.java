package com.kitm.library.api.book;

import com.kitm.library.api.book.dto.BookDto;
import com.kitm.library.api.book.dto.UpsertBookDto;
import com.kitm.library.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IBookService extends ICrudService<BookDto, UpsertBookDto> {
}
