import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import KakaoButton from "../components/KakaoButton";
const Home = () => {
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
    <div>
      <KakaoButton onClick={handleButtonClick} label="로그인 페이지 이동" />
    </div>
  );
};
export default Home;
