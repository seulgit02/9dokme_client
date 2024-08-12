package com.example.server_9dokme.book.service;

import com.example.server_9dokme.book.dto.response.BookCheckDto;
import com.example.server_9dokme.book.dto.response.BookWebViewDto;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookCheckDto checkBook(Long bookId){

        Book book = bookRepository.findByBookId(bookId);

        BookCheckDto dto = BookCheckDto.builder().
                bookId(book.getBookId()).
                title(book.getTitle()).
                author(book.getAuthor()).
                description(book.getDescription()).
                publisher(book.getPublisher()).
                category(book.getCategory()).
                pdfImage(book.getBookImage()).
                category(book.getCategory()).build();



            return dto;
    }

    public Page<BookDto> searchBook(String title, int pageNo){

        Pageable pageable = PageRequest.of(pageNo,4);

        Page<Book> bookPage = bookRepository.findByTitleContaining(title,pageable);

        Page<BookDto> bookDtoPage = bookPage.map(book -> new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getCategory(),
                book.getBookURL(),
                book.getBookImage()));



        return bookDtoPage;
    }


    public BookWebViewDto bookWebView(Long bookId){
        Book book = bookRepository.findByBookId(bookId);


        return BookWebViewDto.builder().
                title(book.getTitle()).
                category(book.getCategory()).
                author(book.getAuthor()).
                pdfUrl(book.getBookURL()).
                build();
    }


}
