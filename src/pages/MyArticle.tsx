import * as React from "react";
import { useState } from "react";
import { Input } from "../components/ui/input";
import { Button } from "../components/ui/button";
import MyPostDetail from "../components/MyPosttDetail"; // MyPostDetail 컴포넌트 임포트
import Sidebanner from "../components/Sidebanner";
// 목데이터를 가져옵니다.
import myPostsData from "../json/MyWrittenText.json";
import myPostDetailData from "../json/MyPostDetail.json"; // 상세조회 목데이터 임포트

interface HistoryItem {
  question: number;
  title: string;
  createdAt: string;
}

interface MyPostsData {
  data: {
    history: HistoryItem[];
  };
}

const MyArticle: React.FC = () => {
  const [selectedQuestion, setSelectedQuestion] = useState<HistoryItem | null>(
    null
  );
  const [isDetailView, setIsDetailView] = useState<boolean>(false); // 상세 조회 상태 추가
  const [searchTitle, setSearchTitle] = useState<string>("");

  const myPosts: MyPostsData = myPostsData;

  const handlePostClick = (post: HistoryItem) => {
    setSelectedQuestion(post);
    setIsDetailView(true); // 상세 조회 상태로 전환
  };

  const handleBack = () => {
    setIsDetailView(false); // 목록 보기로 전환
  };

  const filteredPosts = myPosts.data.history.filter((post) =>
    post.title.toLowerCase().includes(searchTitle.toLowerCase())
  );

  const postDetail = myPostDetailData.data; // 상세 조회 데이터

  return (
    <div className="w-screen h-[100vh] bg-customColor bg-opacity-20 p-[2vw] relative">
      <Sidebanner />
      <div className="max-w-[800px] mx-auto bg-white p-[2vw] rounded-lg shadow-lg">
        {isDetailView && selectedQuestion ? (
          <MyPostDetail postDetail={postDetail} onBack={handleBack} />
        ) : (
          <>
            <div className="text-center font-bold text-[2vw] mb-[1vw]">
              나의 작성글
            </div>
            <div className="flex w-full mb-[2vw]">
              <Input
                className="flex-grow m-[0.5vw]"
                placeholder="게시글 제목을 통해 검색해보세요."
                value={searchTitle}
                onChange={(e) => setSearchTitle(e.target.value)}
              />
              <Button
                variant="outline"
                className="border-[#C5B5F7] text-[1vw] hover:bg-[#C5B5F7] m-[0.5vw]"
              >
                검색
              </Button>
            </div>
            <div className="h-full flex flex-col overflow-y-auto">
              {filteredPosts.length > 0 ? (
                filteredPosts.map((post) => (
                  <CommunityBox
                    key={post.question}
                    title={post.title}
                    content={post.createdAt}
                    commentsCount={0} // 댓글 수는 없으므로 0으로 설정
                    chapter=""
                    onClick={() => handlePostClick(post)} // 클릭 시 상세 조회로 이동
                  />
                ))
              ) : (
                <p className="text-center text-gray-500">
                  해당 제목의 작성글이 없습니다.
                </p>
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
};

const CommunityBox: React.FC<{
  title: string;
  content: string;
  commentsCount: number;
  chapter: string;
  onClick: () => void;
}> = ({ title, content, commentsCount, chapter, onClick }) => {
  return (
    <div
      className="m-[1vw] bg-white shadow-lg rounded-lg p-4 border-solid border-[0.05vw] border-slate-300 mt-[1.5vw]"
      onClick={onClick}
    >
      <h2 className="font-bold text-[1.5vw]">{title}</h2>
      <hr className="mt-[0.5vw] mb-[0.5vw] border-slate-400" />
      <p className="text-gray-400 text-[1vw]">
        {content.length > 20 ? `${content.substring(0, 20)}...` : content}
      </p>
      <div className="flex justify-between items-center mt-4">
        <span className="text-gray-500 text-[1vw]">댓글 {commentsCount}</span>
        {chapter && (
          <span className="text-[1vw] bg-customColor rounded px-[0.5vw]">
            {chapter}
          </span>
        )}
      </div>
    </div>
  );
};

export default MyArticle;
