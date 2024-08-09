import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "./pages/Main";
import LoginPage from "./pages/Login";
import LoginLoading from "./pages/LoginLoading";
import Home from "./pages/Home";
function App() {
  return (
    <Routes>
      <Route path='/' element={<Main />} />
      <Route path='/home' element={<Home />} />
      <Route path='/logintest' element={<LoginPage />} />
      <Route
        path='/login/oauth2/callback/kakao'
        element={<LoginLoading />} 
      />
    </Routes>
  );
}

export default App;
