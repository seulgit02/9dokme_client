import React from "react";
import styled from "styled-components";
import onBookmark from "../images/onBookmark.png";
import offBookmark from "../images/offBookmark.png";
import defaultCover from "../images/bookcover.png";
interface BookCardProps {
  cover: string; // 이미지 URL
  title: string; // 책 제목
  onClick: () => void; // 클릭 이벤트 핸들러
  isMarked: boolean; // 즐겨찾기 상태
}

{
  /* <BookImage src={book.pdfImage} alt={`Cover of ${book.title}`} /> */
}
const BookCard: React.FC<BookCardProps> = ({
  cover,
  title,
  isMarked,
  onClick,
}) => {
  const coverSrc = cover ? cover : defaultCover;

  return (
    <div style={{ position: "relative" }}>
      <BookmarkIcon
        src={isMarked ? onBookmark : offBookmark}
        alt="Bookmark Icon"
      />
      <CardContainer onClick={onClick}>
        <CoverImageWrapper>
          <CoverImage alt={title} src={cover} />
        </CoverImageWrapper>
        <Title>{title.length > 15 ? `${title.slice(0, 15)}...` : title}</Title>
      </CardContainer>
    </div>
  );
};

const BookmarkIcon = styled.img`
  position: absolute;
  top: -7px;
  right: 2px;
  width: 30px;
  height: 30px;
  cursor: pointer;
  z-index: 1;
`;

const CoverImageWrapper = styled.div`
  position: relative;
  width: 100%;
  height: 0;
  padding-top: 150%;
  overflow: hidden;
  border-radius: 10px 10px 0 0;
`;

const CoverImage = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px;
`;

const Title = styled.div`
  padding: 12px;
  text-align: center;
  font-size: 16px;
  font-weight: bold;
  color: #333;
`;

const CardContainer = styled.div`
  width: 200px;
  height: 300px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
  margin-bottom: 20px;
  overflow: visible;

  &:hover {
    transform: scale(1.02);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
  }
`;

export default BookCard;
