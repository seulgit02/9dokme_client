import styled from "styled-components";

interface HashtagProps {
  onCategoryChange: (category: string) => void;
  selectedCategory: string | null;
}

const Hashtag = ({ onCategoryChange, selectedCategory }: HashtagProps) => {
  const categories = [
    "전체보기",
    "공학",
    "자연",
    "예술",
    "인문/사회",
    "체육",
    "경영/경제",
  ];

  return (
    <div className="flex justify-center items-center flex-wrap gap-4 mx-8">
      {categories.map(function (category) {
        return (
          <TagBtn
            key={category}
            className={`hover:bg-[#C5B5F7] ${
              selectedCategory === category ? "bg-[#C5B5F7]" : ""
            }`}
            onClick={() => onCategoryChange(category)}
          >
            <p className="">
              {category === "전체보기" ? "" : "#"}
              {category}
            </p>
          </TagBtn>
        );
      })}

    </div>
  );
};


const TagBtn = styled.button`

  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin: 0 1vw;
  width: 9vw;
  height: 3vw;
  border-radius: 3vw;
  border-style: solid;
  border-width: 1px;
  border-color: #2519b2;
  padding: 1vw;
  font-size: 1.2vw;
`;

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

export default Hashtag;
