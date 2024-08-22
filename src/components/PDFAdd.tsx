import styled from "styled-components";

const PDFAdd: React.FC = () => (
  <Box>
    <form className="w-[65vw] h-auto">
      <div className="grid grid-cols-[1fr_8fr] gap-x-[1vw] gap-y-[1.5vw]">
        <Tag className="text-[1.2vw]">교재 이름</Tag>
        <StyledInput type="text" />

        <Tag className="text-[1.2vw]">카테고리</Tag>
        <StyledSelect>
          <option value="">카테고리 선택</option>
          <option value="science">과학</option>
          <option value="nature">자연</option>
          <option value="art">예술</option>
          <option value="humanities">인문/사회</option>
          <option value="sports">체육</option>
          <option value="business">경영/경제</option>
        </StyledSelect>

        <Tag className="text-[1.2vw]">표지 이미지</Tag>
        <StyledInput type="file" accept=".png, .jpg, .jpeg" />

        <Tag className="text-[1.2vw]">저자</Tag>
        <StyledInput type="text" />

        <Tag className="text-[1.2vw]">PDF URL</Tag>
        <StyledInput />

        <Tag className="text-[1.2vw]">교재설명</Tag>
        <StyledInput as="textarea" />
      </div>
      <AddPdfBtn>PDF 등록하기</AddPdfBtn>
    </form>
  </Box>
);

const Box = styled.div`
  width: 75vw;
  height: 35vw;
  border: 1px solid black;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  background-color: rgba(197, 181, 247, 0.4);
  border-top: none;
`;

const StyledInput = styled.input`
  border: 1px solid black;
  border-radius: 0.2vw;
  padding: 0.5vw;
  font-size: 1vw;

  &:focus {
    outline: none; /* 포커스 스타일 제거 */
    border: 1px solid black; /* 포커스 시 동일한 테두리 유지 */
  }
`;

const StyledSelect = styled.select`
  border: 1px solid black;
  border-radius: 0.2vw;
  padding: 0.5vw;
  font-size: 1vw;

  &:focus {
    outline: none; /* 포커스 스타일 제거 */
    border: 1px solid black; /* 포커스 시 동일한 테두리 유지 */
  }
`;

const Tag = styled.div`
  width: auto;
  height: 2vw;
  border-style: none;
  border-radius: 0.5vw;
  background-color: #d8e7ff;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const AddPdfBtn = styled.button`
  margin-top: 2vw;
  width: 65vw;
  height: 3vw;
  background-color: #2519b2;
  color: white;
  font-weight: bold;
  text-align: center;
  border-radius: 0.3vw;
  font-size: 1.2vw;
`;
export default PDFAdd;
