package com.example.server_9dokme.member.service;

import com.example.server_9dokme.book.entity.Advertisement;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.AdvertisementRepository;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import com.example.server_9dokme.member.entity.Member;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@NoArgsConstructor
@Service
@Getter
public class MemberService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private OrderedFormContentFilter formContentFilter;


    public MainPageDto getMainPage(String category, int pageNo){

        List<Advertisement> advertisementDtoList = advertisementRepository.findAll();

        Pageable pageable = PageRequest.of(pageNo,4);

        Page<Book> bookPage;

        if (category == null || category.isEmpty()) {
            bookPage = bookRepository.findAll(pageable);
        } else {
            bookPage = bookRepository.findAllByCategory(category, pageable);
        }

        // Convert Page<Book> to Page<BookDto>
        Page<BookDto> bookDtoPage = bookPage.map(book -> new BookDto(
                book.getBookId(),
                book.getCategory(),
                book.getBookURL(),
                book.getBookImage()));


        return new MainPageDto(advertisementDtoList,bookDtoPage);
    }


    // 현재 사용자 정보 가져오기
//    public Member getCurrentMember() {
//        return (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }

    // 테스트용 하드코딩 (멤버 아이디를 1로 고정하여 테스트)

    public Member getCurrentMember() {
        if (isTestEnvironment()) {
            Member testMember = new Member();
            testMember.setMemberId(1L);  // Member의 ID를 명확하게 설정
            return testMember;
        }
        return (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private boolean isTestEnvironment() {
        // 테스트 환경 확인을 위해 필요하다면 환경 변수를 체크하거나 단순히 true를 반환
        return true;
    }

}