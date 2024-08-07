package com.example.server_9dokme.member.service;

import com.example.server_9dokme.book.entity.Advertisement;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.AdvertisementRepository;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class MemberService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;


    public MainPageDto getMainPage(String socialId, String category){

        List<Book> bookList = new ArrayList<>();
        List<Advertisement> advertisementDtoList = advertisementRepository.findAll();


        if(category==null || category.equals("")){
            bookList = bookRepository.findAll();
        }else if(category=="공학"){
            bookList = bookRepository.findAllByCategory("공학");

        }else if(category=="자연"){
            bookList = bookRepository.findAllByCategory("자연");
        }else if(category=="예술"){
            bookList = bookRepository.findAllByCategory("예술");
        }else if(category=="인문/사화") {
            bookList = bookRepository.findAllByCategory("인문/사회");
        }else if(category=="체육"){
            bookList = bookRepository.findAllByCategory("체육");
        }else if(category=="경영/경제"){
            bookList = bookRepository.findAllByCategory("경영/경제");
        }

        List<BookDto> bookDtoList = bookList.stream().map(book ->
                BookDto.builder()
                        .bookId(book.getBookId())
                        .category(book.getCategory())
                        .bookImage(book.getBookImage())
                        .bookUrl(book.getBookURL())
                        .build()
        ).collect(Collectors.toList());



        return new MainPageDto(advertisementDtoList,bookDtoList);
    }
}