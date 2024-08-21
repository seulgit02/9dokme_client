import React, { useEffect, useState } from "react";
import profile from "../images/profile.png";
import User from "../json/User.json";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import bg from "../images/bg.png";
import BookCard from "../components/BookCard"; // BookCard 컴포넌트 import
import axios from "axios";
import Sidebanner from "../components/Sidebanner";

const MyPage = () => {
  interface Book {
    bookId: number;
    title: string;
    bookImage: string;
    bookUrl: string;
  }

  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [expiredAt, setExpiredAt] = useState("");

  const navigate = useNavigate();

  const handleClickSubscribeBtn = () => {
    navigate("/subscribe");
  };

  const handleImgClick = (bookId: number) => {
    navigate(`/bookdetail/${bookId}`);
  };

  useEffect(() => {
    const fetchMypageData = async () => {
      try {
        const memberId = localStorage.getItem("memberId");

        if (memberId) {
          const response = await axios.get(`http://localhost:8080/api/mypage`, {
            params: { memberId },
          });

          const { profileDto, bookList } = response.data;
          setBooks(bookList.content); // bookList.content를 books 상태에 저장
          setExpiredAt(profileDto.expirationDate);
          console.log("Profile Data:", profileDto);
          console.log("Book List Data:", bookList);
        } else {
          console.error("memberId not found in localStorage");
        }
      } catch (error) {
        console.error("Error fetching mypage data:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchMypageData();
  }, []);

  return (
    <div className='w-screen h-[130vh] bg-customColor bg-opacity-20'>
      <Sidebanner />
      <div className='flex flex-col justify-center items-center'>
        <img src={profile} className='w-[10vw] mt-[5vw]' alt='Profile' />
        <p className='text-[1.2vw] mt-[2vw] text-center'>
          <span className='text-sky-600 font-bold'>
            {localStorage.getItem("name")}
          </span>
          <span className='font-bold'>님, 안녕하세요:)</span>
          <br />
          구독 만료일은
          <span className='text-red-400 font-bold'>{expiredAt}</span>
          입니다.
        </p>
        <SubscribeBtn onClick={handleClickSubscribeBtn}>
          구독 연장하기
        </SubscribeBtn>
        <BgContainer>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <div
              style={{
                display: "flex",
                flexWrap: "wrap",
                justifyContent: "center",
              }}
            >
              {books.map((book) => (
                <BookCard
                  key={book.bookId}
                  cover={book.bookImage} // bookImage를 cover로 매핑
                  title={book.title}
                  onClick={() => handleImgClick(book.bookId)} // 핸들러에서 book.bookId를 인자로 전달
                />
              ))}
            </div>
          )}
        </BgContainer>
      </div>
    </div>
  );
};

const BgContainer = styled.div`
  background-image: url(${bg});
  background-size: 100% auto;
  background-repeat: no-repeat;
  padding: 2vw;
  margin-top: 2vw;
`;

const SubscribeBtn = styled.button`
  background: linear-gradient(
    90deg,
    #ffe3e4 0%,
    #ffabb5 22%,
    #ffa6b3 45%,
    #ff8f91 55%,
    #f5666f 79%,
    #ff3f43 100%
  );
  color: white;
  border: none;
  padding: 0.8vw 5vw;
  border-radius: 8px;
  cursor: pointer;
  outline: none;
  font-size: 1.2vw;
  font-weight: bold;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: opacity 0.3s;
  margin-top: 2vw;

  &:hover {
    opacity: 0.8;
  }
`;

export default MyPage;
