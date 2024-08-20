package com.example.server_9dokme.bookmark.service;

import com.example.server_9dokme.bookmark.entity.Bookmark;
import com.example.server_9dokme.bookmark.exception.BookmarkException;
import com.example.server_9dokme.bookmark.message.ErrorMessage;
import com.example.server_9dokme.bookmark.repository.BookmarkRepository;
import com.example.server_9dokme.bookmark.service.dto.request.BookMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.request.BookUnMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.response.BookmarkResponse;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    @Autowired
    private  BookmarkRepository bookmarkRepository;
    @Autowired
    private  MemberService memberService;
    @Autowired
    private  BookService bookService;

    @Transactional
    public BookmarkResponse mark(BookMarkRequest request) {
        Member member = memberService.getCurrentMember();
        Book book = bookService.findById(request.bookId()).orElseThrow();

        // 이미 북마크가 존재하는지 확인
        if (bookmarkRepository.findByBookAndMember(book, member).isPresent()) {
            throw new BookmarkException(ErrorMessage.ALREADY_BOOKMARKED);
        }

        Bookmark bookmark = bookmarkRepository.save(new Bookmark(member, book));
        return BookmarkResponse.of(book.getBookId(), true);
    }

    @Transactional
    public BookmarkResponse unmark(BookUnMarkRequest request) {
        Member member = memberService.getCurrentMember();
        Book book = bookService.findById(request.bookId()).orElseThrow();

        Bookmark bookmark = bookmarkRepository.findByBookAndMember(book, member)
                .orElseThrow(() -> new BookmarkException(ErrorMessage.NOT_FOUND_BOOKMARK));

        bookmarkRepository.delete(bookmark);
        return BookmarkResponse.of(book.getBookId(), false);
    }
}