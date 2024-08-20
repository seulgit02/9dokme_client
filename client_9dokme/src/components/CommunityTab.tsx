import community from "../images/community.png";
import communitytalk from "../images/communitytalk.png";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { QuestionList, Question } from "../json/Community";
import questionData from "../json/Community.json";
import styled from "styled-components";
import DetailPost from "./DetailPost";
import CreatePost from "./CreatePost";
import { Input } from "./ui/input";
import { Button } from "./ui/button";

const Sidebanner = () => {
  const questions: QuestionList = questionData;
  // const navigate = useNavigate();
  // const location = useLocation();
  const [selectedQuestionId, setSelectedQuestionId] = useState<number | null>(
    null
  );
  const [isClicked, setIsClicked] = useState(false);
  const [createPostBtn, setCreatePostBtn] = useState(false);
  const [searchQuery, setSearchQuery] = useState<string>("");
  const [filterPost, setFilterPost] = useState<Question[]>(
    questions.questionList
  );
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

  const selectedPost = filterPost.find(
    (post) => post.questionId === selectedQuestionId
  );
  // const handleSearchInputChange = (
  //   event: React.ChangeEvent<HTMLInputElement>
  // ) => {
  //   setSearchQuery(event.target.value);
  // };

  // const handleSearchBtnClick = () => {
  //   const filtered = questions.questionList.filter((post: Question) => {
  //     const matchSearchQuery = post.title
  //       .toLowerCase()
  //       .includes(searchQuery.toLowerCase());
  //     return matchSearchQuery;
  //   });
  //   setFilterPost(filtered);
  // };
  // console.log(questions);

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
            className=" w-[20vw] fixed top-[2vw] right-[3vw]"
            onClick={handleBannerClickOn}
          />
        </div>
      ) : (
        <div className="pointer w-[30vw] h-full bg-white rounded-bl-[2vw]  text-[1.5vw] shadow-sm">
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
                  <div className="font-bold ml-[1vw] text-[1.2vw]">
                    커뮤니티 게시판
                  </div>
                  <div className="flex justify-center items-center m-[1vw]">
                    <Input
                      className="m-[1vw]"
                      placeholder="게시글 제목을 통해 검색해보세요."
                    />
                    <Button variant="outline">검색</Button>
                  </div>
                  <div className="h-[100%] overflow-y-auto">
                    {filterPost.slice(0, 4).map((post) => (
                      <CommunityBox
                        title={post.title}
                        content={post.content}
                        commentsCount={post.commentCount}
                        chapter={post.chapter}
                        onClick={() => handlePostClick(post.questionId)}
                      />
                    ))}
                    <WritingBtn
                      onClick={handleCreatePostBtnClick}
                      className="cursor-pointer"
                    >
                      글 작성하기
                    </WritingBtn>
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

const WritingBtn = styled.div`
  margin-top: 3vw;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: #2519b2;
  height: 4vw;
  font-weight: bold;
  color: white;
  font-size: 1.5vw;
`;
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
export default Sidebanner;
