// import { useState, useEffect } from "react";
// import SlidingBanner from "../components/SlidingBanner";
// import Hashtag from "../components/Hashtag";
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
// import Sidebanner from "../components/Sidebanner";
// import { PRIMARY } from "../utils/colors";
// import { Button, Card, Input } from "antd";
// import styled from "styled-components";
// import BookCard from "../components/BookCard";
// import API from "../api/axios";

// const images: { [key: string]: string } = {
//   "book1.png": book1,
//   "book2.png": book2,
//   "book3.png": book3,
//   "book4.png": book4,
//   "book5.png": book5,
//   "book6.png": book6,
//   "book7.png": book7,
// };

// interface ApiResponse {
//   status: string;
//   data: any;
// }

// const Main = () => {
//   const navigate = useNavigate();
//   const handleImgClick = (bookId: number) => {
//     navigate(`/bookdetail/${bookId}`);
//   };
//   const [category, setCategory] = useState<string>("전체보기");
//   const [searchQuery, setSearchQuery] = useState<string>("");
//   const [filterBooks, setFilterBooks] = useState<Book[]>(bookData.bookData);
//   const [loading, setLoading] = useState<boolean>(true);
//   const handleHashBtnClick = (newCategory: string) => {
//     setCategory(newCategory);
//   };

//   //응답용 인터페이스
//   interface ApiResponse {
//     data: {
//       content: Book[]; // 서버 응답에 맞게 `content` 배열이 Book 타입을 포함하도록 정의
//       totalElements: number;
//       totalPages: number;
//       // 필요한 경우 다른 필드 추가
//     };
//     status: string;
//   }

//   // Book 타입은 이미 정의되어 있다고 가정
//   interface Book {
//     bookId: number;
//     title: string;
//     category: string;
//     bookImage: string;
//     bookUrl: string;
//     isMarked: boolean;
//   }

//   // data 호출 코드
//   const fetchBooks = async () => {
//     try {
//       setLoading(true);
//       const response = await API.get<ApiResponse>(
//         "/api/mainpage/search?title=" + searchQuery
//       );
//       setFilterBooks(response.data.data.content); // 'content' 내부의 데이터를 'filterBooks' 상태에 저장
//       console.log("fetchedBooksData", response.data.data.content); // 콘솔로 데이터 확인
//       setLoading(false);
//     } catch (error: any) {
//       console.error("Failed to fetch books:", error.message);
//       setLoading(false);
//     }
//   };

//   const handleSearchInputChange = (
//     event: React.ChangeEvent<HTMLInputElement>
//   ) => {
//     setSearchQuery(event.target.value);
//   };

//   const handleSearchBtnClick = () => {
//     const filtered = bookData.bookData.filter((book: Book) => {
//       const matchCategory =
//         category === "전체보기" || book.category === category;
//       const matchSearchQuery = book.title
//         .toLowerCase()
//         .includes(searchQuery.toLowerCase());
//       return matchCategory && matchSearchQuery;
//     });
//     setFilterBooks(filtered);
//   };
//   //category 상태 변경시
//   useEffect(() => {
//     if (category) {
//       console.log("category: ", category);
//       handleSearchBtnClick();
//     }
//   }, [category]);

//   useEffect(() => {
//     fetchBooks(); // 컴포넌트 마운트 시 책 데이터 불러오기
//   }, []);

//   return (
//     <>
//       <Root>
//         <Sidebanner />
//         <Container>
//           <SlidingBanner />
//           <BookContainer>
//             <form
//               onSubmit={(e) => {
//                 e.preventDefault();
//                 handleSearchBtnClick();
//               }}
//             >
//               <SearchContainer>
//                 <SearchBox
//                   placeholder="도서를 검색해보세요!"
//                   onChange={handleSearchInputChange}
//                 />
//                 <SearchButton onClick={handleSearchBtnClick}>검색</SearchButton>
//               </SearchContainer>
//             </form>
//             <div className="m-3">
//               <Hashtag
//                 onCategoryChange={handleHashBtnClick}
//                 selectedCategory={category}
//               />
//             </div>
//             <div className="flex justify-center">
//               {filterBooks.length === 0 ? (
//                 <NoneBook>검색 결과가 없습니다.</NoneBook>
//               ) : (
//                 <BooksContainer>
//                   <div className="grid grid-cols-4 gap-10 m-4">
//                     {filterBooks.map((book) => (
//                       <BookCard
//                         key={book.bookId}
//                         cover={images[book.bookUrl]}
//                         title={book.bookTitle}
//                         onClick={() => handleImgClick(book.bookId)}
//                         isMarked={book.isMarked}
//                       />
//                     ))}
//                   </div>
//                 </BooksContainer>
//               )}
//             </div>
//           </BookContainer>
//         </Container>
//       </Root>
//     </>
//   );
// };
// const Root = styled.div`
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
// `;
// const Container = styled.div`
//   width: 100%;
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
//   background-color: ${PRIMARY.LiGHT};
// `;

// const NoneBook = styled.div`
//   width: 100%;
//   margin: 10vw 0;
// `;
// const BookContainer = styled.div`
//   width: 70%;
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   justify-content: center;
// `;

// const BooksContainer = styled.div`
//   margin-top: 70px;
//   margin-bottom: 100px;
//   display: flex;
//   justify-content: center;
// `;
// const SearchContainer = styled.div`
//   width: 100%;
//   padding-top: 60px;
//   padding-bottom: 60px;
//   display: flex;
//   align-items: center;
//   justify-content: center;
// `;
// const SearchBox = styled(Input)`
//   width: 850px;
//   height: 45px;
//   font-size: 16px;
//   &:hover,
//   &:focus {
//     border-color: ${PRIMARY.DEFAULT};
//     box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
//   }
// `;

// const SearchButton = styled(Button)`
//   margin-left: 1vw;
//   height: 45px;
//   width: 110px;
//   font-size: 16px;
//   background-color: ${PRIMARY.DEFAULT};
//   color: white;
//   border: none;
//   padding: 10px;
//   border-radius: 5px;
//   cursor: pointer;

//   &:hover,
//   &:focus {
//     background-color: ${PRIMARY.DEFAULT};
//   }
// `;

// export default Main;
import { useState, useEffect } from "react";
import axios from "axios";
import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
import { Book } from "../json/BookList"; // 가정: BookList.json에서 Book 인터페이스를 export
import { useNavigate } from "react-router-dom";
import book1 from "../images/books/book1.png";
import book2 from "../images/books/book2.png";
import book3 from "../images/books/book3.png";
import book4 from "../images/books/book4.png";
import book5 from "../images/books/book5.png";
import book6 from "../images/books/book6.png";
import book7 from "../images/books/book7.png";
import Sidebanner from "../components/Sidebanner";
import { PRIMARY } from "../utils/colors";
import { Button, Input } from "antd";
import styled from "styled-components";
import BookCard from "../components/BookCard";

const images: { [key: string]: string } = {
  "book1.png": book1,
  "book2.png": book2,
  "book3.png": book3,
  "book4.png": book4,
  "book5.png": book5,
  "book6.png": book6,
  "book7.png": book7,
};

interface ApiResponse {
  content: Book[];
}

const Main = () => {
  const navigate = useNavigate();
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [books, setBooks] = useState<Book[]>([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get<ApiResponse>("/api/mainpage/search", {
          params: { title: searchQuery },
        });
        setBooks(response.data.content);
        console.log("Books data:", response.data.content);
      } catch (error) {
        console.error("Failed to fetch books:", error);
      }
    };

    fetchBooks();
  }, [searchQuery]); // searchQuery가 변경될 때마다 새로운 데이터를 불러옵니다.

  const handleSearchInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchQuery(event.target.value);
  };

  const handleImgClick = (bookId: number) => {
    navigate(`/bookdetail/${bookId}`);
  };

  return (
    <>
      <Root>
        <Sidebanner />
        <Container>
          <SlidingBanner />
          <BookContainer>
            <SearchContainer>
              <SearchBox
                placeholder="도서를 검색해보세요!"
                value={searchQuery}
                onChange={handleSearchInputChange}
              />
              <SearchButton>검색</SearchButton>
            </SearchContainer>
            <BooksContainer>
              {books.map((book) => (
                <BookCard
                  bookId={book.bookId}
                  key={book.bookId}
                  cover={images[book.bookImage]}
                  title={book.title}
                  onClick={() => handleImgClick(book.bookId)}
                  isMarked={book.isMarked}
                />
              ))}
            </BooksContainer>
          </BookContainer>
        </Container>
      </Root>
    </>
  );
};

// Styled Components
const Root = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: ${PRIMARY.LiGHT};
`;
const BookContainer = styled.div`
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
const BooksContainer = styled.div`
  margin-top: 70px;
  margin-bottom: 100px;
  display: flex;
  justify-content: center;
`;
const SearchContainer = styled.div`
  width: 100%;
  padding-top: 60px;
  padding-bottom: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
`;
const SearchBox = styled(Input)`
  width: 850px;
  height: 45px;
  font-size: 16px;
  &:hover,
  &:focus {
    border-color: ${PRIMARY.DEFAULT};
    box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
  }
`;
const SearchButton = styled(Button)`
  margin-left: 1vw;
  height: 45px;
  width: 110px;
  font-size: 16px;
  background-color: ${PRIMARY.DEFAULT};
  color: white;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
  &:hover,
  &:focus {
    background-color: ${PRIMARY.DEFAULT};
  }
`;

export default Main;
