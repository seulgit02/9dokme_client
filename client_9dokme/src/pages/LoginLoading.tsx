import axios from 'axios';
import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const LoginLoading = () => {
  const code = new URL(window.location.href).searchParams.get("code");
  const navigate = useNavigate();

  useEffect(() => {
    const kakaoLogin = async () => {
      try {
        console.log('Authorization code:', code); // 인가 code 출력
        const res = await axios.get(`http://localhost:8080/api/oauth?code=${code}`);
        
        console.log(res.data);
        // 계속 쓸 정보들
        localStorage.setItem("name", res.data.message.nickname);
        localStorage.setItem("email", res.data.message.email);
        // 로그인이 성공하면 이동할 페이지
        navigate("/");
      } catch (error) {
        console.error("Login failed:", error);
      }
    };

    if (code) {
      kakaoLogin();
    }
  }, [code, navigate]);

  return (
    <div className="w-screen h-screen flex items-center justify-center bg-customColor bg-opacity-20">
      <p className="text-xl font-semibold">로그인 중입니다 ...</p>
    </div>
  );
};

export default LoginLoading;
