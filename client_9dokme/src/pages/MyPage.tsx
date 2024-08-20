import profile from "../images/profile.png";
import User from "../json/User.json";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import bg from "../images/bg.png";
import BookList from "../components/BookList";
import Sidebanner from "../components/Sidebanner";
const MyPage = () => {
  const navigate = useNavigate();
  const handleClictSubscribeBtn = () => {
    navigate("/subscribe");
  };
  return (
    <div className="w-screen h-[130vh] bg-customColor bg-opacity-20">
      <Sidebanner />
      <div className="flex flex-col justify-center items-center">
        <img src={profile} className="w-[10vw] mt-[5vw]" />
        <p className="text-[1.2vw] mt-[2vw] text-center">
          <span className="text-sky-600 font-bold">{User.username}</span>
          <span className="font-bold">님, 안녕하세요:)</span>
          <br />
          구독 만료일은
          <span className="text-red-400 font-bold">{User.expirationDate}</span>
          입니다.
        </p>
        <SubscribeBtn onClick={handleClictSubscribeBtn}>
          구독 연장하기
        </SubscribeBtn>
        <BgContainer>
          <BookList />
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
