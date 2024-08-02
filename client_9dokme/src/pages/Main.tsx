import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import KakaoButton from "../components/KakaoButton";
import axios from 'axios'; // Axios import 추가

const Main = () => {
  useEffect(() => {
    // localStorage에 저장된 값을 모두 콘솔에 출력
    const accessToken = localStorage.getItem('accessToken');
    const email = localStorage.getItem('email');
    console.log('Token:', accessToken);
    console.log('Email:', email);
  }, []);

  const kakaoLogout = () => {
    axios.get('http://localhost:8080/api/logout'/* , { withCredentials: true } */)
        .then(response => {
            if (response.data.success) {
                console.log('로그아웃 성공:', response.data.message);
                // 로그아웃 후 리디렉션
                window.location.href = '/home'; // 로그인 페이지로 리디렉션
                
            } else {
                console.log('로그아웃 실패:', response.data.message);
            }
        })
        .catch(error => {
            console.error('Axios error:', error);
        });
  }
  
  const navigate = useNavigate();
  const handleLoginButtonClick = () => {
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
      <KakaoButton onClick={handleLoginButtonClick} label="로그인 페이지 이동" />
      <KakaoButton onClick={kakaoLogout} label="로그아웃" /> {/* 로그아웃 버튼의 onClick 핸들러 수정 */}
    </div>
  );
};

export default Main;
