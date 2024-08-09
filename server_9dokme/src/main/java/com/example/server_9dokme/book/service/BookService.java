package com.example.server_9dokme.book.service;

import com.example.server_9dokme.book.dto.response.BookListDto;
import com.example.server_9dokme.book.dto.response.MyPageDto;
import com.example.server_9dokme.book.dto.response.ProfileDto;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;

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
                book.getCategory(),
                book.getBookURL(),
                book.getBookImage()));

        return new MyPageDto(profileDto,bookDtoPage);
//        return bookDtoPage;
    }
}
