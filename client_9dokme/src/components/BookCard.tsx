// // BookCard.js (JavaScript로 변경)
// import React from "react";
// import styled from "styled-components";
// import { Card } from "antd";
// import onBookmark from "../images/onBookmark.png";
// import offBookmark from "../images/offBookmark.png";
// import { useNavigate } from "react-router-dom";

// const { Meta } = Card;

// interface BookCardProps {
//   cover: string; // 이미지 URL
//   title: string; // 책 제목
//   onClick: () => void; // 클릭 이벤트 핸들러
//   isMarked: boolean;
// }

// const BookCard: React.FC<BookCardProps> = ({
//   cover,
//   title,
//   isMarked,
//   onClick,
// }) => {
//   return (
//     <StyledCard hoverable onClick={onClick}>
//       <BookmarkIcon
//         src={isMarked ? onBookmark : offBookmark}
//         alt="Bookmark Icon"
//       />
//       <img
//         alt={title}
//         src={cover}
//         style={{ height: "16vw", borderRadius: "10px" }}
//       />
//       <Meta title={title.length > 11 ? `${title.slice(0, 11)}...` : title} />
//     </StyledCard>
//   );
// };

// const BookmarkIcon = styled.img`
//   position: absolute;
//   top: 10px;
//   right: 10px;
//   width: 30px;
//   height: 30px;
// `;

// const StyledCard = styled(Card)`
//   width: 13vw;
//   height: 20vw;
//   margin: 2vw;
//   overflow: hidden;
//   border-radius: 10px;

//   img {
//     border-radius: 10px;
//     height: 16vw;
//   }

//   .ant-card-meta-title {
//     text-align: center;
//     font-size: 1.1vw;
//     margin-bottom: 0.2vw;
//     font-weight: bold;
//   }

//   &:before {
//     content: "";
//     position: absolute;
//     top: 0;
//     left: 0;
//     width: 100%;
//     height: 100%;
//     background-color: #f0f0f0;
//     border-radius: 10px;
//     opacity: 0.1;
//     z-index: -1;
//     filter: blur(8px);
//   }
// `;

// export default BookCard;

import React from "react";
import styled from "styled-components";
import { Card } from "antd";
import onBookmark from "../images/onBookmark.png";
import offBookmark from "../images/offBookmark.png";

const { Meta } = Card;

interface BookCardProps {
  cover: string; // 이미지 URL
  title: string; // 책 제목
  onClick: () => void; // 클릭 이벤트 핸들러
  isMarked: boolean; // 즐겨찾기 상태
}

const BookCard: React.FC<BookCardProps> = ({
  cover,
  title,
  isMarked,
  onClick,
}) => {
  return (
    <StyledCard hoverable onClick={onClick}>
      <CoverImageWrapper>
        <CoverImage alt={title} src={cover} />
        <BookmarkIcon
          src={isMarked ? onBookmark : offBookmark}
          alt="Bookmark Icon"
        />
      </CoverImageWrapper>
      <Meta title={title.length > 15 ? `${title.slice(0, 15)}...` : title} />
    </StyledCard>
  );
};

const BookmarkIcon = styled.img`
  position: absolute;
  top: 10px;
  right: 10px;
  width: 30px;
  height: 30px;
`;

const CoverImageWrapper = styled.div`
  position: relative;
  width: 100%;
  height: 0;
  padding-top: 150%;
`;

const CoverImage = styled.img`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 10px 10px 0 0; // Rounded top corners only
`;

const StyledCard = styled(Card)`
  width: 100%;
  background-color: transparent;
  overflow: hidden;
  border-radius: 10px;
  border-style: none;
  .ant-card-body {
    padding: 12px;
  }
  .ant-card-meta-title {
    text-align: center;
    font-size: large;
    font-weight: bold;
  }
`;

export default BookCard;
