import { PostDetail, PostList, Comment } from "../json/PostDetail";
import PostData from "../json/PostDetail.json";
import styled from "styled-components";
import back from "../images/back.png";
import AddComment from "./AddComment";
import { useState } from "react";
interface Props {
  questionId: number;
  chapter: string;
  setSelectedQuestionId: React.Dispatch<React.SetStateAction<number | null>>;
  handleBannerClickOff: () => void;
}
const DetailPost: React.FC<Props> = ({
  questionId,
  chapter,
  setSelectedQuestionId,
  handleBannerClickOff,
}) => {
  const [onBackBtn, setOnBackBtn] = useState<boolean>(false);

  const handleClickOnBackBtn = () => {
    setOnBackBtn(!onBackBtn);
  };

  const filteredPost = PostData.PostDetail.find((post) => {
    return post.questionId == questionId;
  });
  const handleBackBtnClick = () => {
    setSelectedQuestionId(null);
  };
  return (
    <div className="fixed top-0 right-0 z-50 pointer w-[30vw] h-auto bg-white rounded-bl-[2vw] text-[1.5vw] shadow-sm">
      <div className="flex justify-between pl-[2vw] pr-[2vw] pt-[1vw]">
        <img
          className="w-[2vw]"
          src={back}
          alt="arrow"
          onClick={handleBackBtnClick}
        />
        <div
          className="font-bold cursor-pointer"
          onClick={handleBannerClickOff}
        >
          x
        </div>
      </div>
      {filteredPost ? (
        <div className="m-[1vw] bg-white shadow-lg rounded-lg p-4 border-solid border-[0.05vw] border-slate-300 mt-[1.5vw]">
          <h2 className="font-bold text-[1vw]">{filteredPost.title}</h2>
          <span className=" text-[1vw] bg-customColor rounded px-[0.5vw]">
            {chapter}
          </span>
          <br />{" "}
          <p className="text-[1vw] mt-[0.3vw] text-slate-500">
            작성자: {filteredPost.nickName}
          </p>
          <br />
          <p className=" text-[1vw] ">{filteredPost.content}</p>
          <hr className="mt-[1vw] mb-[0.5vw] border-slate-400" />
          {filteredPost.commentList.length > 0 ? (
            filteredPost.commentList.map((comment) => (
              <div key={comment.commentId} className="m-[1vw]">
                <p className="text-[1vw] text-slate-600">
                  {comment.nickName || "익명"}
                </p>
                <p className="text-[1vw]">{comment.comment}</p>
                <hr className="mt-[1vw] mb-[0.5vw] border-slate-400" />
              </div>
            ))
          ) : (
            <>
              <div className="h-[10vw] font-bold flex justify-center items-center text-[1vw]">
                아직 작성된 댓글이 없습니다. <br />
                첫번째로 댓글을 남겨보세요! 👇
              </div>
            </>
          )}
        </div>
      ) : (
        <div>해당 게시물을 찾을 수 없습니다.</div>
      )}
      <div className="w-[100%]">
        {onBackBtn === true ? (
          <AddComment onBackFn={handleClickOnBackBtn} />
        ) : (
          <WritingBtn onClick={handleClickOnBackBtn}>댓글 남기기</WritingBtn>
        )}
      </div>
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
  cursor: pointer;
`;
export default DetailPost;
