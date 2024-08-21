import styled from "styled-components";
import AdminBanner from "../components/AdminBanner";
import QueryData from "../json/Query.json";
import { Inquiry, InquiryResponse, SortInfo, Pageable } from "../json/Query";
import React, { useState, useEffect } from 'react';
import QueryDetail from "../components/QueryDetail";
import API from "../api/axios";
import {
  Table,
  TableHeader,
  TableBody,
  TableFooter,
  TableHead,
  TableRow,
  TableCell,
  TableCaption,
} from "../components/ui/table";
import axios from 'axios';
import ReactPaginate from 'react-paginate';

const AdminQboard = () => {
  const [openEditor, setOpenEditor] = useState<boolean>(false);
  const [selectedQuery, setSelectedQuery] = useState<Inquiry | null>(null);
  const [queryData, setQueryData] = useState<any>({ content: [], totalPages: 0 });
  const [currentPage, setCurrentPage] = useState<number>(0);

  const fetchQueries = async (pageNo: number) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/admin/inquiries/${pageNo}`);
      setQueryData(response.data);
      console.log(response.data);
    } catch (error) {
      console.log("API 호출 오류: ", error);
      alert("데이터를 가져오는 데 오류가 발생했습니다.");
    }
  };

  useEffect(() => {
    fetchQueries(currentPage);
  }, [currentPage]);

  const onBack = () => {
    setOpenEditor(false);
    setSelectedQuery(null);
  };

  const detailViewClick = (query: Inquiry) => {
    setSelectedQuery(query);
    setOpenEditor(true);
  };

  const onDeleteClick = async (inquireId: string) => {
    try {
      const response = await axios.delete(
        `/api/admin/inquire/delete/${inquireId}`
      );
      if (response.status === 200 || response.status === 201) {
        alert(`${inquireId}: 문의글이 삭제되었습니다.`);
        fetchQueries(currentPage); // 삭제 후 데이터 새로고침
      } else {
        alert("문의글 삭제에 실패했습니다.");
      }
    } catch (error) {
      console.log("문의글 삭제 오류: ", error);
      alert("오류가 발생했습니다. 나중에 다시 시도해주세요.");
    }
  };

  const handlePageClick = (data: { selected: number }) => {
    setCurrentPage(data.selected);
  };

  return (
    <div className="flex-col h-screen bg-customColor bg-opacity-20 flex justify-center items-center">
      {openEditor && selectedQuery && (
        <QueryDetail query={selectedQuery} onBack={onBack} />
      )}

      <AdminBanner />
      <h1 className="font-bold">문의게시판 관리 페이지</h1>
      <Table className="bg-white m-[3vw] w-[60vw] ml-[20vw]">
        <TableHeader>
          <TableRow className="bg-[#D8E7FF]">
            <TableHead className="w-[10vw]">문의글 제목</TableHead>
            <TableHead className="w-[30vw]">문의글 내용</TableHead>
            <TableHead className="w-[10vw]">조회</TableHead>
            <TableHead className="w-[7vw]">삭제</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {queryData.content.map((query: Inquiry) => (
            <TableRow key={query.inquireId} className="text-left">
              <TableCell className="font-medium">{query.title}</TableCell>
              <TableCell className="font-medium">
                {query.content.length > 20
                  ? query.content.slice(0, 20) + "..."
                  : query.content}
              </TableCell>

              <TableCell>
                <button
                  onClick={() => detailViewClick(query)}
                  className="bg-slate-400 text-[0.8vw] text-white px-[0.5vw] rounded hover:bg-customColor3"
                >
                  상세조회
                </button>
              </TableCell>
              <TableCell>
                <button
                  onClick={() => onDeleteClick(String(query.inquireId))}
                  className="bg-slate-400 text-[0.8vw] text-white px-[0.5vw] rounded hover:bg-[#FF7E7E]"
                >
                  삭제
                </button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

<PageContainer>
      <ReactPaginate
        previousLabel={"이전"}
        nextLabel={"다음"}
        breakLabel={"..."}
        pageCount={queryData.totalPages}
        marginPagesDisplayed={2}
        pageRangeDisplayed={5}
        onPageChange={handlePageClick}
        containerClassName={"pagination"}
        pageClassName={"page-item"}
        pageLinkClassName={"page-link"}
        previousClassName={"page-item"}
        previousLinkClassName={"page-link"}
        nextClassName={"page-item"}
        nextLinkClassName={"page-link"}
        breakClassName={"page-item"}
        breakLinkClassName={"page-link"}
        activeClassName={"active"}
      />
      </PageContainer>
    </div>
  );
  
};


const Box = styled.div`
  width: 75vw;
  height: auto;
  border: 0.2vw solid black;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  background-color: transparent;
  background-color: rgba(197, 181, 247, 0.4);
  border-top: none;
  overflow-x: auto;
`;
const PageContainer = styled.div`
  align-items: center;
  display:flex;
  flex-direction: row;
`


export default AdminQboard;
