import book from "../json/books.json";
import bookData from "../json/books.json";
import { Book } from "../json/BookList";
import { useNavigate } from "react-router-dom";
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
  const bookList: Book[] = bookData.bookData;
  const navigate = useNavigate();
  const handleImgClick = (bookId: number) => {
    navigate(`/api/bookdetail/${bookId}`);
  };
  return (
    <div className="flex justify-center ">
      <div className="grid grid-cols-5 gap-4 m-4">
        {bookList.map((book: Book) => (
          <div
            key={book.bookId}
            className="flex flex-col items-center align-center"
            onClick={() => handleImgClick(book.bookId)}
          >
            <img
              className="w-[10vw] m-[2vw] rounded-lg text-center"
              key={book.bookId}
              //src={require(`../images/books/${book?.bookImage}`)}
              src={images[book.bookUrl]}
              alt={book.bookTitle}
            />
            <div className="text-sm font-medium w=[5vw] text-center">
              [
              {book.bookTitle.length > 11
                ? book.bookTitle.slice(0, 11) + "..."
                : book.bookTitle}
              ]
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
export default BookList;
