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
import { PRIMARY } from "../utils/colors";
import { Button, Card, Input } from "antd";
import styled from "styled-components";


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
    navigate(`/bookdetail/${bookId}`);
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
      <Root>
        <Sidebanner />
        <Container>
          <SlidingBanner />
          <BookContainer>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                handleSearchBtnClick();
              }}
            >
              <SearchContainer>
                <SearchBox
                  placeholder='도서를 검색해보세요!'
                  onChange={handleSearchInputChange}
                />
                <SearchButton onClick={handleSearchBtnClick}>검색</SearchButton>
              </SearchContainer>
            </form>
            <div className='m-3'>
              <Hashtag
                onCategoryChange={handleHashBtnClick}
                selectedCategory={category}
              />
            </div>
            <div className="flex justify-center">
      {filterBooks.length === 0 ? (
        <NoneBook>검색 결과가 없습니다.</NoneBook>
      ) : (
        <div className="grid grid-cols-4 gap-4 m-4">
          {filterBooks.map((book) => (
            <BookCard
              key={book.bookId}
              cover={images[book.bookUrl]}
              title={book.bookTitle}
              onClick={() => handleImgClick(book.bookId)}
            />
          ))}
        </div>
      )}
    </div>
          </BookContainer>
        </Container>
      </Root>
    </>
  );
};
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

const NoneBook = styled.div`
 width: 100%;
 margin: 10vw 0;
`
const BookContainer = styled.div`
  width: 70%;
  display: flex;
  flex-direction: column;
  align-items: center;
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

const { Meta } = Card;

interface BookCardProps {
  cover: string;
  title: string;
  onClick: () => void;
}

const StyledCard = styled(Card)`
  width: 13vw;
  height: 20vw;
  margin: 2vw;
  overflow: hidden;
  border-radius: 10px;

  img {
    border-radius: 10px;
    height: 16vw;
  }

  .ant-card-meta-title {
    text-align: center;
    font-size: 1.1vw;
    margin-bottom: 0.2vw;
    font-weight: bold;
  }

  &:before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: ${PRIMARY.DEFAULT};
    border-radius: 10px;
    opacity: 0.1;
    z-index: -1;
    filter: blur(8px);
  }
`;

const BookCard: React.FC<BookCardProps> = ({ cover, title, onClick }) => {
  return (
    <StyledCard
      hoverable
      cover={<img alt={title} src={cover} />}
      onClick={onClick}
    >
      <Meta title={title.length > 11 ? `${title.slice(0, 11)}...` : title} />
    </StyledCard>
  );
};

export default Main;