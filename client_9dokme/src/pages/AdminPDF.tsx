import { useState } from "react";
import PDFAdd from "../components/PDFAdd";
import PDFEdit from "../components/PDFEdit";
import PDFDelete from "../components/PDFDelete";
import styled from "styled-components";
import AdminBanner from "../components/AdminBanner";

// 상태 기반 조건부 렌더링
const AdminPDF: React.FC = () => {
  const [activeTab, setActiveTab] = useState<"add" | "edit" | "delete">("add");

  const handleTabChange = (tab: "add" | "edit" | "delete") => {
    setActiveTab(tab);
  };

  return (
    <div className="w-screen h-[100vh] bg-customColor bg-opacity-20">
      <AdminBanner />
      <div className="text-center w-full h-[7vw] flex items-center justify-center font-bold text-[2vw]">
        PDF 관리 페이지
      </div>
      <div className="tab-buttons flex justify-center items-center w-full">
        <OptionTab
          className={`tab-button ${activeTab === "add" ? "active" : ""}`}
          onClick={() => handleTabChange("add")}
        >
          PDF 추가
        </OptionTab>
        <OptionTab
          className={`tab-button ${activeTab === "edit" ? "active" : ""}`}
          onClick={() => handleTabChange("edit")}
        >
          PDF 수정
        </OptionTab>
        <OptionTab
          className={`tab-button ${activeTab === "delete" ? "active" : ""}`}
          onClick={() => handleTabChange("delete")}
        >
          PDF 삭제
        </OptionTab>
      </div>
      <div className="text-center w-full flex items-center justify-center font-bold mt-[-0.1vw]">
        {/* 마이너스 마진을 사용해 Tab 바와 바로 아래에 붙도록 설정 */}
        {activeTab === "add" && <PDFAdd />}
        {activeTab === "edit" && <PDFEdit />}
        {activeTab === "delete" && <PDFDelete />}
      </div>
    </div>
  );
};

const OptionTab = styled.button`
  width: 25vw;
  height: 6vw;
  border-radius: 1vw 1vw 0 0; /* 위쪽 모서리 둥글게 */
  border: 1px solid black;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 1.7vw;
  background-color: transparent;
  cursor: pointer;

  &.active {
    background-color: rgba(197, 181, 247, 0.4);
    border-bottom: none;
  }
`;

export default AdminPDF;
