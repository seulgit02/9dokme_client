package com.example.server_9dokme.member.service;

import com.example.server_9dokme.book.entity.Advertisement;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.AdvertisementRepository;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Data
@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private final HttpSession session;
    @Autowired
    private final MemberRepository memberRepository;
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
                book.getTitle(),
                book.getCategory(),
                book.getBookURL(),
                book.getBookImage()));


        return new MainPageDto(advertisementDtoList,bookDtoPage);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

//    public Member saveMember(Member member) {
//        return memberRepository.save(member);
//    }

    // 현재 사용자 정보 가져오기
    public Member getCurrentMember() {
        String email = (String) session.getAttribute("email");
        if (email == null) {
            log.error("User not logged in. Session attributes: {}", session.getAttributeNames());
            throw new IllegalStateException("User not logged in.");
        }
        return findMemberByEmail(email);
    }

    private Member findMemberByEmail(String email) {
        Member member = memberRepository.findBySocialId(email);
        if (member == null) {
            log.error("No member found with email: {}", email);
            throw new IllegalArgumentException("No member found with the provided email.");
        }
        return member;
    }
}