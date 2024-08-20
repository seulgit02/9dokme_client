import React from "react";
interface Props {
  onBackFn: () => void;
}

const AddComment: React.FC<Props> = ({ onBackFn }) => {
  return (
    <div className="pointer w-[30vw] h-auto bg-white rounded-bl-[2vw] text-[1.5vw] shadow-sm">
      <div className="m-[2vw]">
        <div className="flex flex-col mb-[1vw]">
          <label className="text-[1vw] col-span-2 mb-[1vw]">
            📬 댓글을 작성해주세요.
          </label>
          <textarea className="col-span-2 p-[0.5vw] text-[1vw] h-[10vw] border border-gray-300 rounded resize-none" />
        </div>
      </div>
      <div className="mt-[2vw] ">
        <button className="bg-customColor3 text-white p-[1vw] text-[1vw] w-[50%] font-bold">
          작성 완료하기
        </button>
        <button
          onClick={onBackFn}
          className="bg-gray-300 text-black p-[1vw] text-[1vw] w-[50%] font-bold"
        >
          작성 취소하기
        </button>
      </div>
    </div>
  );
};

export default AddComment;
