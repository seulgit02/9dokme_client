package com.example.server_9dokme.member.service;

import com.example.server_9dokme.book.entity.Advertisement;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.repository.AdvertisementRepository;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.dto.response.MainPageDto;
import com.example.server_9dokme.member.dto.response.PostWrittenDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.question.dto.response.CommentDto;
import com.example.server_9dokme.question.entity.Question;
import com.example.server_9dokme.question.repository.CommentRepository;
import com.example.server_9dokme.question.repository.QuestionRepository;
import jakarta.servlet.http.HttpSession;
import com.example.server_9dokme.member.dto.response.MemberDto;
import com.example.server_9dokme.member.repository.MemberRepository;
import com.example.server_9dokme.rent.entity.Rent;
import com.example.server_9dokme.rent.repository.RentRepository;
import com.example.server_9dokme.subscribe.entity.Subscribe;
import com.example.server_9dokme.subscribe.repository.SubscribeRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private HttpSession session;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private OrderedFormContentFilter formContentFilter;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private SubscribeRepository subscribeRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommentRepository commentRepository;


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

    public Page<MemberDto> getMemberList(int pageNo){
        Pageable pageable = PageRequest.of(pageNo,10);
        Page<Member> memberList = memberRepository.findAll(pageable);

        //expireDate추가 필요!!
        Page<MemberDto> MemberDtoPage = memberList.map(member -> {
            // Subscribe 객체를 가져옴
            Subscribe subscribe = subscribeRepository.findByMember_MemberId(member.getMemberId());

            // expiredAt이 null이면 "미구독"으로 설정
            String expiredAt = (subscribe != null && subscribe.getExpiredAt() != null)
                    ? subscribe.getExpiredAt().toString()
                    : "미구독";

            return new MemberDto(
                    member.getMemberId(),
                    member.getNickName(),
                    member.getSocialId(),
                    expiredAt
            );
        });

        return MemberDtoPage;
    }

    public void deleteMember(Long memberId){
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
        } else {
            throw new RuntimeException("Member with ID " + memberId + " not found");
        }
    }


    public Page<PostWrittenDto> getPostWrittenList(Object socialId, int pageNo){
        String email = socialId.toString();

        Pageable pageable = PageRequest.of(pageNo,6);

        Page<Question> myQuestionList = questionRepository.findAllByEmail(email, pageable);

        List<PostWrittenDto> myPostWrittenList = myQuestionList.stream()
                .map(question -> PostWrittenDto.builder()
                        .questionId(question.getQuestionId())
                        .bookId(question.getBook() != null ? question.getBook().getBookId() : null)  // Null 체크
                        .title(question.getTitle())
                        .content(question.getContent())
                        .chapter(question.getChapter())
                        .commentCount(commentRepository.countByQuestion_QuestionId(question.getQuestionId()))
                        .build())
                .collect(Collectors.toList());

        // PageImpl을 사용하여 변환된 리스트와 페이지 정보를 함께 반환
        return new PageImpl<>(myPostWrittenList, pageable, myQuestionList.getTotalElements());
    }



}