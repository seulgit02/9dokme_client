"use client";

import * as React from "react";
import community from "../images/community.png";
import communitytalk from "../images/communitytalk.png";
import { useState, useEffect } from "react";
import axios from "axios"; // Import axios for API requests
import styled from "styled-components";
import DetailPost from "./DetailPost";
import CreatePost from "./CreatePost";
import { Input } from "./ui/input";
import { Button } from "./ui/button";

import { DropdownMenuCheckboxItemProps } from "@radix-ui/react-dropdown-menu";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "./ui/dropdown-menu";

type Checked = DropdownMenuCheckboxItemProps["checked"];

interface Book {
  bookId: number;
  bookTitle: string;
  author: string;
  bookCategory: string;
  bookURL: string;
}

interface CommunityTabProps {
  book?: Book;
}

interface Question {
  questionId: number;
  title: string;
  content: string;
  chapter: number;
  bookPage: number;
  commentCount: number;
  createdAt: string;
}

interface QuestionList {
  questionList: Question[];
}

const CommunityTab: React.FC<CommunityTabProps> = ({ book }) => {
  const [questions, setQuestions] = useState<QuestionList>({
    questionList: [],
  });
  const [selectedQuestionId, setSelectedQuestionId] = useState<number | null>(
    null
  );
  const [isClicked, setIsClicked] = useState(false);
  const [createPostBtn, setCreatePostBtn] = useState(false);
  const [searchTitle, setSearchTitle] = useState<string>("");
  const [searchChapter, setSearchChapter] = useState<string>("");
  const [searchPage, setSearchPage] = useState<string>("");
  const [filterPost, setFilterPost] = useState<Question[]>([]);

  useEffect(() => {
    if (book) {
      const fetchQuestions = async () => {
        try {
          const response = await axios.get(`/api/questionlist/${book.bookId}`, {
            params: {
              chapter: searchChapter,
              bookPage: searchPage,
            },
          });
          setQuestions(response.data);
          setFilterPost(response.data.questionList);
        } catch (error) {
          console.error("Error fetching questions:", error);
        }
      };

      fetchQuestions();
    }
  }, [book, searchChapter, searchPage]);

  const handlePostClick = (questionId: number) => {
    setSelectedQuestionId(questionId);
  };

  const handleBannerClickOn = () => {
    if (!isClicked) setIsClicked(true);
  };

  const handleBannerClickOff = () => {
    if (isClicked) setIsClicked(false);
  };

  const handleCreatePostBtnClick = () => {
    setCreatePostBtn(true);
  };

  const handleBackFromWriting = () => {
    setCreatePostBtn(false); // 글 작성 상태 비활성화`
  };

  const handleSearch = () => {
    const filtered = questions.questionList.filter((post) => {
      const matchTitle =
        searchTitle === "" ||
        post.title.toLowerCase().includes(searchTitle.toLowerCase());
      const matchChapter =
        searchChapter === "" ||
        post.chapter.toString().toLowerCase() === searchChapter.toLowerCase();
      const matchPage =
        searchPage === "" ||
        post.bookPage
          .toString()
          .toLowerCase()
          .includes(searchPage.toLowerCase());
      return matchTitle && matchChapter && matchPage;
    });

    setFilterPost(filtered.length > 0 ? filtered : questions.questionList);
  };

  const selectedPost = filterPost.find(
    (post) => post.questionId === selectedQuestionId
  );

  return (
    <div className="fixed top-0 right-0 z-50">
      {!isClicked ? (
        <div className="flex">
          <img
            src={community}
            className="w-[3vw] fixed top-[2vw] right-[2vw]"
            onClick={handleBannerClickOn}
          />
          <img
            src={communitytalk}
            className="w-[20vw] fixed top-[2vw] right-[3vw]"
            onClick={handleBannerClickOn}
          />
        </div>
      ) : (
        <div className="pointer w-[30vw] h-full bg-white rounded-bl-[2vw] text-[1.5vw] shadow-sm">
          <div
            onClick={handleBannerClickOff}
            className="text-right font-bold cursor-pointer p-[1vw]"
          >
            x
          </div>

          {createPostBtn ? (
            <CreatePost onBack={handleBackFromWriting} />
          ) : (
            <>
              {selectedQuestionId === null ? (
                <>
                  <div className="font-bold text-[1.2vw] ml-[1vw]">
                    커뮤니티 게시판
                  </div>
                  <div className="flex flex-col justify-center items-center m-[1vw]">
                    <div className="flex w-full mb-[1vw]">
                      <Input
                        className="flex-grow m-[0.5vw]"
                        placeholder="게시글 제목을 통해 검색해보세요."
                        value={searchTitle}
                        onChange={(e) => setSearchTitle(e.target.value)}
                      />
                    </div>
                    <div className="flex w-full">
                      <DropdownMenu>
                        <DropdownMenuTrigger className="border border-[#C5B5F7] rounded-[0.5vw] bg-white p-[0.4vw] w-full text-[1vw] flex-grow m-[0.5vw]">
                          {searchChapter === ""
                            ? "전체챕터 ▼"
                            : `${searchChapter} ▼`}
                        </DropdownMenuTrigger>
                        <DropdownMenuContent className="bg-[#ffffff]">
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("")}
                          >
                            전체챕터
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("0")}
                          >
                            ch0
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("1")}
                          >
                            ch1
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("2")}
                          >
                            ch2
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("5")}
                          >
                            ch5
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            onClick={() => setSearchChapter("6")}
                          >
                            ch6
                          </DropdownMenuItem>
                        </DropdownMenuContent>
                      </DropdownMenu>
                      <Input
                        className="flex-grow m-[0.5vw]"
                        placeholder="페이지를 검색하세요."
                        value={searchPage}
                        onChange={(e) => setSearchPage(e.target.value)}
                      />
                      <Button
                        variant="outline"
                        className="border-[#C5B5F7] text-[1vw] hover:bg-[#C5B5F7] m-[0.5vw]"
                        onClick={handleSearch}
                      >
                        검색
                      </Button>
                    </div>
                  </div>

                  <div className="h-full flex flex-col overflow-y-auto">
                    <div className="flex-grow overflow-y-auto">
                      {filterPost.map((post) => (
                        <CommunityBox
                          key={post.questionId}
                          title={post.title}
                          content={post.content}
                          commentsCount={post.commentCount}
                          chapter={post.chapter}
                          onClick={() => handlePostClick(post.questionId)}
                        />
                      ))}
                    </div>
                    <TabContainer>
                      <WriteButton
                        onClick={handleCreatePostBtnClick}
                        className="cursor-pointer"
                      >
                        글 작성하기
                      </WriteButton>
                    </TabContainer>
                  </div>
                </>
              ) : (
                selectedPost && (
                  <DetailPost
                    setSelectedQuestionId={setSelectedQuestionId}
                    questionId={selectedQuestionId}
                    chapter={selectedPost.chapter}
                    handleBannerClickOff={handleBannerClickOff}
                  />
                )
              )}
            </>
          )}
        </div>
      )}
    </div>
  );
};

interface CommunityPost {
  title: string;
  content: string;
  commentsCount: number;
  chapter: string;
  onClick: () => void;
}

const CommunityBox: React.FC<CommunityPost> = ({
  title,
  content,
  commentsCount,
  chapter,
  onClick, //onClick 함수 받아오기
}) => {
  return (
    <div
      className="m-[1vw] bg-white shadow-lg rounded-lg p-4 border-solid border-[0.05vw] border-slate-300 mt-[1.5vw]"
      onClick={onClick}
    >
      <h2 className="font-bold text-[1vw]">{title}</h2>
      <hr className="mt-[0.5vw] mb-[0.5vw] border-slate-400" />
      <p className="text-gray-400 text-[1vw]">
        {content.length > 20 ? `${content.substring(0, 20)}...` : content}
      </p>
      <div className="flex justify-between items-center mt-4">
        <span className="text-gray-500 text-[1vw]">댓글 {commentsCount}</span>
        <span className=" text-[1vw] bg-customColor rounded px-[0.5vw]">
          {chapter}
        </span>
      </div>
    </div>
  );
};

const TabContainer = styled.div`
  width: 100%;
  padding: 10px;
  background-color: #f5f5f5;
`;

const WriteButton = styled.button`
  width: 100%;
  padding: 15px;
  font-size: 16px;
  font-weight: bold;
  color: white;
  background-color: #4caf50;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #45a049;
  }
`;
export default CommunityTab;
