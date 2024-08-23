import { useRecoilState } from "recoil";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import banner from "../images/banner.png";
import styled, { keyframes } from "styled-components";
import chat from "../images/banner/chat.png";
import book from "../images/banner/book.png";
import bookmark from "../images/banner/bookmark.png";
import query from "../images/banner/query.png";
import { Typography } from "antd";
import logout from "../images/adminBanner/logout.png";
import API from "../api/axios";
const Sidebanner = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [isClicked, setIsClicked] = useState(false);

  const [activeBtn, setActiveBtn] = useState("bookmark");

  const handleBannerClickOn = () => {
    if (!isClicked) setIsClicked(true);
  };

  const handleBannerClickOff = () => {
    setIsClicked(false);
  };

  const handleLogout = async (): Promise<void> => {
    try {
      // API 호출
      const response = await API.get("/api/logout");
      navigate("/");

      // 응답 로깅
      console.log("Logout successful:", response.data);
    } catch (error) {
      // 오류 처리
      console.error("Logout failed:", error);
    }
  };

  useEffect(() => {
    const pathToKeyMap: { [key: string]: string | undefined } = {
      "/mainpage": "mainpage",
      "/": "landing",
      "/queryBoard": "queryboard",
    };
    const currentKey = pathToKeyMap[location.pathname];
    if (currentKey) {
      setActiveBtn(currentKey);
    }
  }, [location.pathname]);

  const handleNavigate = (path: string, btnKey: string) => {
    setActiveBtn(btnKey);
    navigate(path);
  };

  return (
    <div className="fixed top-0 right-0 z-50">
      {!isClicked ? (
        <img
          src={banner}
          className="w-[3vw] fixed top-[2vw] right-[2vw]"
          onClick={handleBannerClickOn}
        />
      ) : (
        <StyledBannerContainer
          className={!isClicked ? "slide-out" : ""}
          onAnimationEnd={() => {
            if (!isClicked) setIsClicked(false);
          }}
        >
          <MenuContainer>
            <MenuTypo>Menu</MenuTypo>
            <div
              onClick={handleBannerClickOff}
              className="text-right font-bold cursor-pointer"
            >
              x
            </div>
          </MenuContainer>
          <BtnComponent>
            <NavBarBtn
              text="메인 페이지"
              icon={book}
              isActive={activeBtn === "mainpage"}
              onClick={() => handleNavigate("/mainpage", "mainpage")}
            />
            <NavBarBtn
              text="나의 책갈피"
              icon={bookmark}
              isActive={activeBtn === "/mypage"}
              onClick={() => handleNavigate("/mypage", "mypage")}
            />
            <NavBarBtn
              text="나의 작성글"
              icon={chat}
              isActive={activeBtn === "query"}
              onClick={() => handleNavigate("/queryBoard", "query")}
            />
            <NavBarBtn
              text="문의글 작성"
              icon={query}
              isActive={activeBtn === "query"}
              onClick={() => handleNavigate("/queryBoard", "query")}
            />
          </BtnComponent>
        </StyledBannerContainer>
      )}
    </div>
  );
};

interface NavBarBtnProps {
  text: string;
  icon: string;
  onClick: () => void;
  isActive: boolean;
}

interface NavBarBtnStyleProps {
  isActive: boolean;
}

const NavBarBtn: React.FC<NavBarBtnProps> = ({
  text,
  icon,
  onClick,
  isActive,
}) => {
  return (
    <NavBarBtnStyle onClick={onClick} isActive={isActive}>
      {icon && (
        <img
          src={icon}
          alt={text}
          style={{ marginRight: "20px", width: "18%" }}
        />
      )}
      {text}
    </NavBarBtnStyle>
  );
};

const slideIn = keyframes`
  from {
    transform: translateX(100%);
  }
  to {
    transform: translateX(0);
  }
`;

const slideOut = keyframes`
  from {
    transform: translateX(0);
  }
  to {
    transform: translateX(100%);
  }
`;

const StyledBannerContainer = styled.div`
  width: 20vw;
  height: 50vw;
  background-color: white;
  border-radius: 0 0 0 2vw;
  text-align: left;
  font-size: 1.5vw;
  position: fixed;
  right: 0;
  animation: ${slideIn} 0.3s forwards;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);

  &.slide-out {
    animation: ${slideOut} 0.3s forwards;
  }
`;

const NavBarBtnStyle = styled.button<NavBarBtnStyleProps>`
  color: ${(props) => (props.isActive ? "white" : "black")};
  background-color: ${(props) => (props.isActive ? "#5A4BFF" : "#FFFFFF")};
  text-align: left;
  font-weight: bold;
  font-size: 1vw;
  display: flex;
  align-items: center;
  width: 15vw;
  height: 2.3vw;
  padding: 1.5vw;
  border-radius: 0.5vw;
  border-style: none;
  margin: 1vw 0;

  &:hover {
    background-color: #5a4bff;
    color: white;

    & img {
      filter: brightness(0) invert(1);
    }
  }
`;

const BtnComponent = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
`;

const MenuContainer = styled.div`
  padding: 2vw;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const MenuTypo = styled(Typography)`
  margin-left: 0.4vw;
  font-size: 1.7vw;
  font-weight: bold;
`;
export default Sidebanner;
