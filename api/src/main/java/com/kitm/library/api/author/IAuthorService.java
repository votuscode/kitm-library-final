package com.kitm.library.api.author;

import com.kitm.library.api.author.dto.AuthorDto;
import com.kitm.library.api.author.dto.UpsertAuthorDto;
import com.kitm.library.api.common.ICrudService;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
public interface IAuthorService extends ICrudService<AuthorDto, UpsertAuthorDto> {
}
