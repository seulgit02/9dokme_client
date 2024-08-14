package com.example.server_9dokme.book.service;

import com.example.server_9dokme.book.dto.response.BookListDto;
import com.example.server_9dokme.book.dto.response.MyPageDto;
import com.example.server_9dokme.book.dto.response.ProfileDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.book.dto.response.BookCheckDto;
import com.example.server_9dokme.book.dto.response.BookWebViewDto;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Data
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;

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



    public MyPageDto getMypageBookList(int id, int pageNo, String criteria){

        Pageable pageable = PageRequest.of(pageNo, 8, Sort.Direction.DESC, criteria);
        Member member =memberRepository.findByMemberId(id);

        ProfileDto profileDto = new ProfileDto(
                member.getMemberId(),
                member.getNickName()
        );

        Page<Book> page = bookRepository.findAllByMember(member, pageable);

        Page<BookDto> bookDtoPage = page.map(book -> new BookDto(
                book.getBookId(),
                book.getTitle(),
                book.getCategory(),
                book.getBookURL(),
                book.getBookImage()));

        return new MyPageDto(profileDto,bookDtoPage);
//        return bookDtoPage;
    }
}
