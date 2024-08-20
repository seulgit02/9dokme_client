import { useState, useEffect } from "react";
import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
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
import Sidebanner from "../components/Sidebanner";


const images: { [key: string]: string } = {
  "book1.png": book1,
  "book2.png": book2,
  "book3.png": book3,
  "book4.png": book4,
  "book5.png": book5,
  "book6.png": book6,
  "book7.png": book7,
};

const Main = () => {
  const navigate = useNavigate();
  const handleImgClick = (bookId: number) => {
    navigate(`/api/bookdetail/${bookId}`);
  };
  const [category, setCategory] = useState<string>("전체보기");
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [filterBooks, setFilterBooks] = useState<Book[]>(bookData.bookData);
  const handleHashBtnClick = (newCategory: string) => {
    setCategory(newCategory);
  };

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchBtnClick = () => {
    const filtered = bookData.bookData.filter((book: Book) => {
      const matchCategory =
        category === "전체보기" || book.bookCategory === category;
      const matchSearchQuery = book.bookTitle
        .toLowerCase()
        .includes(searchQuery.toLowerCase());
      return matchCategory && matchSearchQuery;
    });
    setFilterBooks(filtered);
  };
  //category 상태 변경시
  useEffect(() => {
    if (category) {
      console.log("category: ", category);
      handleSearchBtnClick();
    }
  }, [category]);
  const bookList: Book[] = bookData.bookData;
  return (
    <>
      <Sidebanner />
      <div className="w-screen h-[100%] bg-customColor bg-opacity-20">
        <SlidingBanner />
        <div className="w-screen h-screen flex flex-col">
          <div className="m-[2vw] text-[1.2vw]">
            도서 검색을 통해 원하는 교재를 검색해보세요!
          </div>
          <form
            className="flex pe-[2vw]"
            onSubmit={(e) => {
              e.preventDefault();
              handleSearchBtnClick();
            }}
          >
            <input
              onChange={handleSearchInputChange}
              className="bg-white-900 rounded-[2vw] ml-[8vw] mt-[0.3vw] mr-[2vw] w-[78.5vw] h-[3vw] mb-[2vw] pl-[1.5vw] text-[1.5vw]"
            />
            <button
              onClick={handleSearchBtnClick}
              className="rounded-[1vw] bg-purple w-[8vw] h-[4vw] hover-effect hover:bg-purple2 text-[1.5vw] font-bold"
            >
              검색
            </button>
          </form>
          <div className="m-3">
            <Hashtag
              onCategoryChange={handleHashBtnClick}
              selectedCategory={category}
            />
          </div>
          <div className="flex justify-center ">
            <div className="grid grid-cols-5 gap-4 m-4">
              {filterBooks
                .filter(
                  (book: Book) =>
                    category === "전체보기" || book.bookCategory === category
                )
                .map((book: Book) => (
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
        </div>
      </div>
    </>
  );
};

export default Main;
