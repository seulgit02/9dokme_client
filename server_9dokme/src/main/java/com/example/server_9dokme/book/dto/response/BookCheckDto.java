package com.example.server_9dokme.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class BookCheckDto {

    private Long bookId;

    private String pdfImage;

    private String title;

    private String author;

    private String category;

    private String publisher;

    private String description;


}
