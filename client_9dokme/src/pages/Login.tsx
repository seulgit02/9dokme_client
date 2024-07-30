import React from 'react';
import KakaoButton from "../components/KakaoButton";
import { KAKAO_AUTH_URL } from '../auth/Auth';

const LoginPage = () => {
  const handleKakaoButtonClick = () => {
    window.location.href = KAKAO_AUTH_URL;
  };

  return (
    <div className="w-screen h-full bg-customColor bg-opacity-20">
      <KakaoButton onClick={handleKakaoButtonClick} label="로그인 버튼" />
    </div>
  );
};

export default LoginPage;
