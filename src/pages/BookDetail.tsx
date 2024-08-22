import axios from "axios";
import styled from "styled-components";
import Sidebanner from "../components/Sidebanner";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BookDetailType, Books } from "../json/BookDetailType";
import books from "../json/BookDetail.json";
import { Divider } from "antd";
import { PRIMARY } from "../utils/colors";
import { BASE_URL } from "../env";
import { message } from "antd";

const BookDetail = () => {
  const [isOpen, setIsOpen] = useState<boolean>(false);

  // const handleBookmarkBtn = () => {
  //   setIsOpen(true);
  // };
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
  const memberId = localStorage.getItem("memberId");

  const handleBookmarkBtn = async () => {
    try {
      console.log(bookId);
      await axios.post(`${BASE_URL}/api/bookmark?BookId=${bookId}&memberId=${memberId}`);
      message.success("북마크에 추가되었습니다!");
    } catch (error) {
      console.error('Error bookmarking the book:', error);
      message.error("이미 북마크에 등록되었습니다.");
    }
    setIsOpen(true);
  };

  return (
    <Root>
      <Sidebanner />
      <Container>
        <BookContainer>
          <BookImage src={require(`../images/books/${book?.bookImage}`)} />
        </BookContainer>
        <ContentContainer>
          <div>
            <BookTypo>{book?.bookTitle}</BookTypo>
            <TagBtn>{book?.bookCategory}</TagBtn>
            <ContentTypo>{book?.description}</ContentTypo>
          </div>
          <Divider
          style={{
            borderColor: '#cacaca',
          }}
           />
          <ButtonContainer>
            <GradientButton onClick={() => handleImgClick(bookId)}>
              PDF 보러가기
            </GradientButton>
            <GradientButton onClick={handleBookmarkBtn}>
              나의 책갈피에 추가하기
            </GradientButton>
          </ButtonContainer>
        </ContentContainer>
      </Container>
    </Root>
  );
};
const Root = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: ${PRIMARY.LiGHT};
`;

const Container = styled.div`
  width: 85%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  padding: 30px;
  margin-top: 80px;
  margin-bottom: 80px;
`;

const BookImage = styled.img`
  width: 15vw;
  border-radius: 8px;
  align-self: center;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
`;

const BookContainer = styled.div`
  width: 35%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const BookTypo = styled.div`
  font-size: 25px;
  font-weight: bold;
`;

const ContentTypo = styled.div`
  font-size: 15px;
`;

const ContentContainer = styled.div`
  width: 65%;
  height: 100%;
  display: flex;
  flex-direction: column;
  border: 1px solid lightgray;
  border-radius: 8px;
  padding: 40px;
  background-color: white;
  height: 500px;
  justify-content: space-between;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;
const GradientButton = styled.button`
  background: linear-gradient(45deg, #6a11cb, #2575fc); /* 그라데이션 효과 */
  width: 45%;
  color: white;
  font-weight: bold;
  border: none;
  border-radius: 8px;
  padding: 1rem 2rem;
  cursor: pointer;
  font-size: 1rem;
  transition: transform 0.2s;

  &:hover {
  }

  &:active {
  }
`;

const TagBtn = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin: 0 1vw;
  width: auto;
  height: 1.2vw;
  border-radius: 3vw;
  border-style: solid;
  border-width: 1px;
  border-color: ${PRIMARY.DEFAULT};
  color: ${PRIMARY.DEFAULT};
  padding: 0.6vw;
  font-size: 0.8vw;
  background-color: white;
  margin: 1vw 1vw 1vw 0vw;
`;

export default BookDetail;
