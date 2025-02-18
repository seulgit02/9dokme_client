package com.example.server_9dokme.book.controller;

import com.example.server_9dokme.book.dto.response.BookCheckDto;
import com.example.server_9dokme.book.dto.response.BookWebViewDto;
import com.example.server_9dokme.book.dto.response.MyPageDto;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.message.ErrorMessage;
import com.example.server_9dokme.book.repository.BookRepository;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.common.dto.BaseResponse;
import com.example.server_9dokme.common.dto.ErrorResponse;
import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/books")
    @Operation(summary = "pdf 교재 상세조회", description = "pdf 교재 상세조회")
    public ResponseEntity<BookCheckDto> getBookDetail(@RequestParam Long id,
                                                      Long memberId) {

        if (memberId == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }
        if(bookRepository.existsById(id)==false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
        }

        BookCheckDto dto = bookService.checkBook(id,memberId);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/mainpage/search")
    @Operation(summary = "pdf 교재 검색", description = "pdf 교재 검색 title 기반")
    public ResponseEntity<Page<BookDto>> searchBookPDF(@RequestParam(defaultValue = "") String title,
                                                       @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                       @RequestParam Long memberId) {

//        Object currentMember = memberRepository.findByMemberId(memberId);
        Page<BookDto> dto = bookService.searchBook(title,pageNo, memberId);

//        if (currentMember == null) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
//        }
//        if(dto.isEmpty()==true){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
//        }
//
//
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @GetMapping("/view")
    @Operation(summary = "pdf 교재 웹뷰 조회", description = "pdf 교재 웹뷰 조회")
    public ResponseEntity<BookWebViewDto> viewBookPDF(@RequestParam Long bookId,
                                                      @RequestParam Long memberId) {

        Member currentMember = memberRepository.findByMemberId(memberId);

        if (currentMember == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }

        if(bookRepository.existsById(bookId)==false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 pdf 교재입니다.");
        }

        BookWebViewDto dto = bookService.bookWebView(bookId);

        bookService.saveRentBook(bookId,currentMember.getSocialId());

        return ResponseEntity.ok(dto);
    }

    @Transactional
    @PutMapping("/view/{bookId}")
    @Operation(summary = "웹 뷰 조회 나가기", description = "웹 뷰 조회를 나가며 진행률 최신화(뒤로가기)")
    public ResponseEntity<String> quitViewBookPDF(@PathVariable Long bookId,
                                            @RequestParam int lastPage,
                                            Long memberId){

        if (memberId == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "로그인 후 이용해주세요.");
        }

        bookService.updateProgress(bookId,memberId,lastPage);

        return ResponseEntity.ok("웹 뷰 종료");
    }

    @GetMapping("/mypage")
    public MyPageDto mypage(@RequestParam Long memberId,
                            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
        return bookService.getMypageBookList(memberId, pageNo);
    }

//    @GetMapping("/mypage")
//    @Operation(summary = "마이페이지", description = "마이페이지,세션기반")
//    public MyPageDto mypage(HttpServletRequest request,
//                            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
//
//        HttpSession session = request.getSession();
//
//        String socialId = session.getAttribute("email").toString();
//
//        return bookService.getMypageBookList(Long.valueOf(socialId), pageNo);
//    }
}

