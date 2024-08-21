import { useState } from "react";
import styled from "styled-components";
import { useParams, useNavigate } from "react-router-dom";
import book1 from "../images/books/book1.png";
import books from "../json/BookDetail.json";
import { BookDetailType, Books } from "../json/BookDetailType";
import Sidebanner from "../components/Sidebanner";

import BookmarkSucces from "../components/BookmarkSuccess";
const BookDescription = () => {
  const handleBookmarkBtn = () => {
    setIsOpen(true);
  };
  const [isOpen, setIsOpen] = useState<boolean>(false);

  const handleModalClose = () => {
    setIsOpen(false);
  };

  const navigate = useNavigate();
  const handleImgClick = (bookId: string | undefined) => {
    const book = books.books.find(
      (b: BookDetailType) => String(b.bookId) === String(bookId)
    );
    navigate(`/view/${bookId}`, { state: { book } });
  };
  const { bookId } = useParams<{ bookId: string }>();
  const book = books.books.find(
    (b: BookDetailType) => String(b.bookId) === String(bookId)
  );
  console.log("book:", book);
  return (
    <div className="w-screen h-screen bg-customColor bg-opacity-20">
      <Sidebanner />

      <div className="w-screen h-[65%]">
        <div className="grid grid-cols-3by5 h-screen">
          <div className="p-20 box-content">
            <img
              src={require(`../images/books/${book?.bookImage}`)}
              alt="book1 mt-10"
              className="w-[15vw] rounded-lg mt-10 self-senter"
            />
          </div>
          <div className="pr-10 box-border">
            <div className="font-bold text-left mt-20 text-[1.5vw] ">
              [{book?.bookTitle}]
              <p className="text-[1.3vw] mt-[0.2vw]">{book?.author}</p>
            </div>
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
          onClick={() => handleImgClick(bookId)}
          className="cursor-pointer border-xl bg-submitColor w-[29vw] h-[4vw] rounded-lg text-white font-semibold text-[1.5vw] ml-20 box-border"
        >
          PDF 보러가기
        </button>
        <GradientDiv
          onClick={handleBookmarkBtn}
          className="border-xl bg-customGradient w-[29vw] h-[4vw] rounded-lg text-white font-semibold text-[1.5vw] mr-20 box-border"
        >
          나의 책갈피에 추가하기
        </GradientDiv>
      </div>
      <BookmarkSucces
        isOpen={isOpen}
        handleModalClose={handleModalClose}
        bookId={bookId}
      />

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
