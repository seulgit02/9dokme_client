import React, { useState } from "react";
import styled from "styled-components";
import { useParams, useNavigate } from "react-router-dom";
import books from "../json/BookDetail.json";
import { BookDetailType } from "../json/BookDetailType";
import Sidebanner from "../components/Sidebanner";
import BookmarkSuccess from "../components/BookmarkSuccess";
import onBookmark from "../images/onBookmark.png"; // Assuming you have these images
import offBookmark from "../images/offBookmark.png"; // Assuming you have these images

import BookmarkSucces from "../components/BookmarkSuccess";
const BookDescription = () => {
  // const handleBookmarkBtn = () => {
  //   setIsOpen(true);
  //   setIsBookmarked(!isBookmarked); // Toggle bookmark status on button click
  // };

  // const handleModalClose = () => {
  //   setIsOpen(false);
  // };

  // const handleImgClick = () => {
  //   const book = books.books.find((b) => String(b.bookId) === bookId);
  //   navigate(`/view/${bookId}`, { state: { book } });
  // };

  // const book = books.books.find((b) => String(b.bookId) === bookId);

  return (
    <div className="w-screen h-screen bg-customColor bg-opacity-20">
      <Sidebanner />

      {/* <div className="w-screen h-[65%]">
        <div className="grid grid-cols-3by5 h-screen">
          <div className="p-20 box-content">
            <img
              src={require(`../images/books/${book?.bookImage}`)}
              alt="Book Cover"
              className="w-[15vw] rounded-lg mt-10 self-center"
            />
          </div>
          <div className="pr-10 box-border flex flex-col">
            <div className="flex items-center mt-20">
              <h1 className="font-bold text-left text-[1.5vw]">
                [{book?.bookTitle}]
              </h1>
              <img
                src={isBookmarked ? onBookmark : offBookmark}
                alt={isBookmarked ? "Bookmarked" : "Not Bookmarked"}
                style={{ width: "24px", marginLeft: "15px" }}
              />
            </div>
            <p className="text-[1.3vw] mt-[0.2vw]">{book?.author}</p>
            <div>
              <TagBtn>{book?.bookCategory}</TagBtn>
            </div>
            <div className="text-left mt-5 text-[1.2vw] me-5 text-slate-600 pe-3vw box-border">
              {book?.description}
            </div>
          </div>
        </div>
      </div>
      <div className="w-screen h-[35%] bg-customColor2 flex justify-between pt-20">
        <button
          onClick={handleImgClick}
          className="cursor-pointer border-xl bg-submitColor w-[29vw] h-[4vw] rounded-lg text-white font-semibold text-[1.5vw] ml-20 box-border"
        >
          PDF 보러가기
        </button>
        {book?.isMarked ? (
          <GradientDiv
            onClick={handleBookmarkBtn}
            className="border-xl bg-slate-600 w-[29vw] h-[4vw] rounded-lg text-white font-semibold text-[1.5vw] mr-20 box-border"
          >
            나의 책갈피 취소하기
          </GradientDiv>
        ) : (
          <GradientDiv
            onClick={handleBookmarkBtn}
            className="border-xl bg-customGradient w-[29vw] h-[4vw] rounded-lg text-white font-semibold text-[1.5vw] mr-20 box-border"
          >
            나의 책갈피에 추가하기
          </GradientDiv>
        )}
      </div>
      <BookmarkSuccess
        isOpen={isOpen}
        handleModalClose={handleModalClose}
        bookId={bookId}
      /> */}
    </div>
  );
};

const TagBtn = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin: 0 1vw;
  width: auto;
  height: 2vw;
  border-radius: 3vw;
  border-style: solid;
  border-width: 1px;
  border-color: #2519b2;
  padding: 1vw;
  font-size: 1vw;
  background-color: #c5b5f7;
  margin: 1vw 1vw 1vw 0vw;
`;

const GradientDiv = styled.div`
  background-image: linear-gradient(
    to right,
    #918fff 0%,
    #5956ff 45%,
    #3f3cff 55%,
    #3431ff 79%,
    #0011ff 100%
  );
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;
export default BookDescription;
