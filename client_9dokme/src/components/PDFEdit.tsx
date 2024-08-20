import styled from "styled-components";
import BookDetail from "../json/BookDetail.json";
import { BookDetailType, Books } from "../json/BookDetailType";
import {
  Table,
  TableHeader,
  TableBody,
  TableFooter,
  TableHead,
  TableRow,
  TableCell,
  TableCaption,
} from "./ui/table";

const PDFEdit: React.FC = () => (
  <Box>
    <Table className="bg-white m-[3vw]">
      <TableHeader>
        <TableRow className="bg-[#D8E7FF]">
          <TableHead className="w-[30vw]">책 제목</TableHead>
          <TableHead className="w-[20vw]">저자</TableHead>
          <TableHead className="w-[13vw]">등록일</TableHead>
          <TableHead className="w-[7vw]">선택</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {BookDetail.books.map((book: BookDetailType) => (
          <TableRow key={book.bookId} className="text-left">
            <TableCell className="font-medium">{book.bookTitle}</TableCell>
            <TableCell>{book.author}</TableCell>
            <TableCell>{book.publishDate}</TableCell>
            <TableCell>
              <button className="bg-slate-400 text-[0.8vw] text-white px-[0.5vw] rounded hover:bg-customColor3">
                수정
              </button>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  </Box>
);

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

export default PDFEdit;
