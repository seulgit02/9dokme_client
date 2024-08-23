import React, { useEffect, useState } from "react";
import profile from "../images/profile.png";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import bg from "../images/bg.png";
import BookCard from "../components/BookCard"; // BookCard 컴포넌트 import
import axios from "axios";
import Sidebanner from "../components/Sidebanner";
import { BASE_URL } from "../env";

const MyPage = () => {
  interface Book {
    bookId: number;
    title: string;
    bookImage: string;
    bookUrl: string;
    isMarked: boolean;
  }

  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState(true);
  const [expiredAt, setExpiredAt] = useState("");
  const [isSubscribed, setIsSubscribed] = useState(false);

  const navigate = useNavigate();

  const handleRedirect = () => {
    // 외부 URL로 리다이렉트
    const baseurl = "https://example.com";
    window.location.href = `${baseurl}/payments`;
  };

  const handleImgClick = (bookId: number) => {
    navigate(`/bookdetail/${bookId}`);
  };

  useEffect(() => {
    const fetchMypageData = async () => {
      try {
        const memberId = localStorage.getItem("memberId");

        if (memberId) {
          const response = await axios.get(`${BASE_URL}/api/mypage`, {
            params: { memberId },
          });

          const { profileDto, bookList } = response.data;
          setBooks(bookList.content);
          setExpiredAt(profileDto.expirationDate);
          setIsSubscribed(profileDto.subscribed); // 구독 상태 설정
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
    <div className="w-screen h-[130vh] bg-customColor bg-opacity-20">
      <Sidebanner />
      <div className="flex flex-col justify-center items-center">
        <img src={profile} className="w-[10vw] mt-[5vw]" alt="Profile" />
        <p className="text-[1.2vw] mt-[2vw] text-center">
          <span className="text-sky-600 font-bold">
            {localStorage.getItem("name")}
          </span>
          <span className="font-bold">님, 안녕하세요:)</span>
          <br />
          {isSubscribed ? (
            <>
              구독 만료일은
              <span className="text-red-400 font-bold mx-2">{expiredAt}</span>
              입니다.
            </>
          ) : (
            <span className="text-gray-400">구독되어 있지 않습니다.</span>
          )}
        </p>

        <SubscribeBtn onClick={handleRedirect}>
          {isSubscribed ? "구독 연장하기" : "구독하기"}
        </SubscribeBtn>

        <BooksContainer>
          {loading ? (
            <p>Loading...</p>
          ) : (
            <div className="grid grid-cols-4 gap-10 m-4">
              {books.map((book) => (
                <BookCard
                  key={book.bookId}
                  cover={book.bookImage}
                  title={book.title}
                  isMarked={book.isMarked}
                  onClick={() => handleImgClick(book.bookId)}
                />
              ))}
            </div>
          )}
        </BooksContainer>
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
const BooksContainer = styled.div`
  margin-top: 70px;
  margin-bottom: 100px;
  display: flex;
  justify-content: center;
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
