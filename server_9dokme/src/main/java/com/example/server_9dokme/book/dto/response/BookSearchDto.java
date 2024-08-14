package com.example.server_9dokme.book.dto.response;

import com.example.server_9dokme.member.dto.response.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Data
@Builder
public class BookSearchDto{
    private Page<BookDto> bookList;
}
