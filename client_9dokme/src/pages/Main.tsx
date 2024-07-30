import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import KakaoButton from "../components/KakaoButton";
const Main = () => {
  useEffect(() => {
    // localStorage에 저장된 값을 모두 콘솔에 출력
    const name = localStorage.getItem('name');
    const email = localStorage.getItem('email');
    console.log('Name:', name);
    console.log('Email:', email);
  }, []);
  
  const navigate = useNavigate();
  const handleButtonClick = () => {
    navigate(`/logintest`);
  };
  return (
    <div className="w-screen h-full bg-customColor bg-opacity-20">
      <SlidingBanner />
      <div className="w-screen h-screen flex flex-col">
        <div className="m-[2vw] text-[1.2vw]">
          도서 검색을 통해 원하는 교재를 검색해보세요!
        </div>
        <input className="bg-white-900 rounded-[2vw] mx-[8vw] h-[3vw] mb-[2vw]" />
        <div className="m-3">
          <Hashtag />
        </div>
      </div>
      <KakaoButton onClick={handleButtonClick} label="로그인 페이지 이동" />
    </div>
  );
};
export default Main;
