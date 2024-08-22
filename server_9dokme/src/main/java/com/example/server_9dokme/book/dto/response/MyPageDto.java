package com.example.server_9dokme.book.dto.response;

import com.example.server_9dokme.member.dto.response.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class MyPageDto {
    private ProfileDto profileDto;
    private Page<BookDto> bookList;
}
