package com.example.server_9dokme.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String category;
    private String bookImage;
    private String bookUrl;
}