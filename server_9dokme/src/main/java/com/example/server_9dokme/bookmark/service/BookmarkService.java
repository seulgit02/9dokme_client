package com.example.server_9dokme.bookmark.service;

import com.example.server_9dokme.book.entity.Book;
import com.example.server_9dokme.book.exception.BookException;
import com.example.server_9dokme.book.service.BookService;
import com.example.server_9dokme.bookmark.entity.Bookmark;
import com.example.server_9dokme.bookmark.exception.BookmarkException;
import com.example.server_9dokme.bookmark.message.ErrorMessage;
import com.example.server_9dokme.bookmark.repository.BookmarkRepository;
import com.example.server_9dokme.bookmark.service.dto.request.BookMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.request.BookUnMarkRequest;
import com.example.server_9dokme.bookmark.service.dto.response.BookmarkResponse;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberService memberService;
    private final BookService bookService;

    @Transactional
    public BookmarkResponse mark(BookMarkRequest bookMarkRequest) {
        Member member = memberService.getCurrentMember();
        Book book = bookService.findById(bookMarkRequest.bookId())
                .orElseThrow(() -> new BookmarkException(ErrorMessage.NOT_FOUND_BOOKMARK));

        // 기존 북마크가 존재하는지 확인
        Long bookmarkCount = bookmarkRepository.countByBook(book);
        if (bookmarkCount > 0) {
            throw new BookmarkException(ErrorMessage.ALREADY_BOOKMARKED);
        }

        Bookmark bookmark = new Bookmark(member, book);
        bookmarkRepository.save(bookmark);

        return new BookmarkResponse(bookmark.getBookmarkId());
    }

    @Transactional
    public BookmarkResponse unmark(BookUnMarkRequest bookUnMarkRequest) {
        Member member = memberService.getCurrentMember();
        Book book = bookService.findById(bookUnMarkRequest.bookId())
                .orElseThrow(() -> new BookmarkException(ErrorMessage.NOT_FOUND_BOOKMARK));

        Bookmark bookmark = bookmarkRepository.findByBookAndMember(book, member)
                .orElseThrow(() -> new BookException(com.example.server_9dokme.book.message.ErrorMessage.NOT_FOUND_BOOK));

        bookmarkRepository.delete(bookmark);

        return new BookmarkResponse(bookmark.getBookmarkId());
    }

}
