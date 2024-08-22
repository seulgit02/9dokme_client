import React from "react";

interface Props {
  onBack: () => void;
}

const CreatePost: React.FC<Props> = ({ onBack }) => {
  return (
    <div className="fixed top-0 right-0 z-50 pointer w-[30vw] h-auto bg-white rounded-bl-[2vw] text-[1.5vw] shadow-sm">
      <div className="m-[2vw]">
        <div className="flex justify-between items-center mb-[3vw] ">
          <div className="font-bold text-[1.2vw] ">커뮤니티 글 작성하기</div>
          <button onClick={onBack} className="font-bold cursor-pointer">
            x
          </button>
        </div>
        <div className="grid grid-cols-[1fr_5fr] gap-x-[1vw] gap-y-[1.5vw]">
          <label className="text-[1.2vw]">제목</label>
          <input
            type="text"
            className="col-span-1 p-[0.5vw] text-[1vw] border border-gray-300 rounded"
          />

          <label className="text-[1.2vw]">챕터</label>
          <input
            type="text"
            className="col-span-1 p-[0.5vw] text-[1vw] border border-gray-300 rounded"
          />

          <label className="text-[1.2vw]">페이지</label>
          <input
            type="text"
            className="col-span-1 p-[0.5vw] text-[1vw] border border-gray-300 rounded"
          />

          <label className="text-[1.2vw] col-span-2">글내용</label>
          <textarea className="col-span-2 p-[0.5vw] text-[1vw] h-[10vw] border border-gray-300 rounded resize-none" />
        </div>
      </div>
      <div className="mt-[2vw] ">
        <button className="bg-customColor3 text-white p-[1vw] text-[1vw] w-[50%] font-bold">
          작성 완료하기
        </button>
        <button
          onClick={onBack}
          className="bg-gray-300 text-black p-[1vw] text-[1vw] w-[50%] font-bold"
        >
          작성 취소하기
        </button>
      </div>
    </div>
  );
};

export default CreatePost;
