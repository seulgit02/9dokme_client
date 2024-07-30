import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "./pages/Main";
import LoginPage from "./pages/Login";
import LoginLoading from "./pages/LoginLoading";
function App() {
  return (
    <Routes>
      <Route path='/' element={<Main />} />
      <Route path='/logintest' element={<LoginPage />} />
      <Route
        path='/login/oauth2/callback/kakao'
        element={<LoginLoading />} 
      />
    </Routes>
  );
}

export default App;
