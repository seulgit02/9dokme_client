import { Route, Routes } from "react-router-dom";
import Main from "./pages/Main";
import BookDiscription from "./pages/BookDiscription";
import Landing from "./pages/Landing";
import PdfViewer from "./pages/PdfViewer";
import QueryBoard from "./pages/QueryBoard";
import MyPage from "./pages/MyPage";
import AdminPDF from "./pages/AdminPDF";
import AdminUser from "./pages/AdminUser";
import AdminQboard from "./pages/AdminQboard";
import MyArticle from "./pages/MyArticle";
import { RecoilRoot } from "recoil";

function App() {
  return (
    <RecoilRoot>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/api/mainPage/" element={<Main />} />
        <Route path="/api/bookdetail/:bookId/" element={<BookDiscription />} />
        <Route path="api/view/:bookId/" element={<PdfViewer />} />
        <Route path="api/queryboard/" element={<QueryBoard />} />
        <Route path="api/mypage/" element={<MyPage />} />
        <Route path="api/myarticle/" element={<MyArticle />} />
        <Route path="api/admin/adminPdf/" element={<AdminPDF />} />
        <Route path="api/admin/adminUser/" element={<AdminUser />} />
        <Route path="api/admin/adminQboard/" element={<AdminQboard />} />
      </Routes>
    </RecoilRoot>

  );
}

export default App;
