// import book from "../json/books.json";
// import bookData from "../json/books.json";
// import { Book } from "../json/BookList";
// import { useNavigate } from "react-router-dom";
// import book1 from "../images/books/book1.png";
// import book2 from "../images/books/book2.png";
// import book3 from "../images/books/book3.png";
// import book4 from "../images/books/book4.png";
// import book5 from "../images/books/book5.png";
// import book6 from "../images/books/book6.png";
// import book7 from "../images/books/book7.png";

// const images: { [key: string]: string } = {
//   "book1.png": book1,
//   "book2.png": book2,
//   "book3.png": book3,
//   "book4.png": book4,
//   "book5.png": book5,
//   "book6.png": book6,
//   "book7.png": book7,
// };
// const BookList = () => {
//   const bookList: Book[] = bookData.bookData;
//   const navigate = useNavigate();
//   const handleImgClick = (bookId: number) => {
//     navigate(`/api/bookdetail/${bookId}`);
//   };
//   return (
//     <div className="flex justify-center ">
//       <div className="grid grid-cols-5 gap-4 m-4">
//         {bookList.map((book: Book) => (
//           <div
//             key={book.bookId}
//             className="flex flex-col items-center align-center"
//             onClick={() => handleImgClick(book.bookId)}
//           >
//             <img
//               className="w-[10vw] m-[2vw] rounded-lg text-center"
//               key={book.bookId}
//               //src={require(`../images/books/${book?.bookImage}`)}
//               src={images[book.bookUrl]}
//               alt={book.bookTitle}
//             />
//             <div className="text-sm font-medium w=[5vw] text-center">
//               [
//               {book.bookTitle.length > 11
//                 ? book.bookTitle.slice(0, 11) + "..."
//                 : book.bookTitle}
//               ]
//             </div>
//           </div>
//         ))}
//       </div>
//     </div>
//   );
// };
// export default BookList;

import React from "react";
import { useNavigate } from "react-router-dom";
import bookData from "../json/books.json"; // 데이터 가져오기
import { Book, BookData } from "../json/BookList"; // Book 및 BookData 타입 가져오기
import onBookmark from "../images/onBookmark.png"; // 북마크된 상태 이미지
import offBookmark from "../images/offBookmark.png"; // 북마크 해제 상태 이미지

// 이미지 경로 매핑
import book1 from "../images/books/book1.png";
import book2 from "../images/books/book2.png";
import book3 from "../images/books/book3.png";
import book4 from "../images/books/book4.png";
import book5 from "../images/books/book5.png";
import book6 from "../images/books/book6.png";
import book7 from "../images/books/book7.png";

const images: { [key: string]: string } = {
  "book1.png": book1,
  "book2.png": book2,
  "book3.png": book3,
  "book4.png": book4,
  "book5.png": book5,
  "book6.png": book6,
  "book7.png": book7,
};

const BookList = () => {
  const books: Book[] = (bookData as BookData).bookData; // 타입 캐스팅 사용
  const navigate = useNavigate();

  const handleImgClick = (bookId: number) => {
    // bookId 타입 명시
    navigate(`/api/bookdetail/${bookId}`);
  };

  return (
    <div className="flex justify-center">
      <div className="grid grid-cols-5 gap-4 m-4">
        {books.map((book: Book) => (
          <div
            key={book.bookId}
            className="flex flex-col items-center align-center relative"
            onClick={() => handleImgClick(book.bookId)}
          >
            <img
              className="w-[10vw] m-[2vw] rounded-lg text-center"
              src={images[book.bookImage]} // book.bookImage로 참조 변경
              alt={book.bookTitle}
            />
            <img
              src={book.isMarked ? onBookmark : offBookmark}
              className="absolute top-0 left-0 w-8 h-8"
              alt={book.isMarked ? "Bookmarked" : "Not Bookmarked"}
            />
            <div className="text-sm font-medium w-[5vw] text-center">
              {book.bookTitle.length > 11
                ? `${book.bookTitle.slice(0, 11)}...`
                : book.bookTitle}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BookList;
