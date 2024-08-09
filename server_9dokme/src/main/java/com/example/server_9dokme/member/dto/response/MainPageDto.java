package com.example.server_9dokme.member.dto.response;

import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.entity.Advertisement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class MainPageDto {

    private List<Advertisement> advertisementList;
    private Page<BookDto> bookList;

}
