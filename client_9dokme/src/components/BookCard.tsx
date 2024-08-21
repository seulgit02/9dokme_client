// BookCard.js (JavaScript로 변경)
import React from "react";
import styled from "styled-components";
import { Card } from "antd";
import { useNavigate } from "react-router-dom";

const { Meta } = Card;

interface BookCardProps {
  cover: string;  // 이미지 URL
  title: string;  // 책 제목
  onClick: () => void;  // 클릭 이벤트 핸들러
}

const BookCard: React.FC<BookCardProps> = ({ cover, title, onClick }) => {
  

  //커버 이미지 연결 필요
  return (
    <StyledCard
      hoverable
      cover={<img alt={title} src={cover} />}
      onClick={onClick}
    >
      <Meta title={title.length > 11 ? `${title.slice(0, 11)}...` : title} />
    </StyledCard>
  );
};

const StyledCard = styled(Card)`
  width: 13vw;
  height: 20vw;
  margin: 2vw;
  overflow: hidden;
  border-radius: 10px;

  img {
    border-radius: 10px;
    height: 16vw;
  }

  .ant-card-meta-title {
    text-align: center;
    font-size: 1.1vw;
    margin-bottom: 0.2vw;
    font-weight: bold;
  }

  &:before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #f0f0f0;
    border-radius: 10px;
    opacity: 0.1;
    z-index: -1;
    filter: blur(8px);
  }
`;

export default BookCard;
