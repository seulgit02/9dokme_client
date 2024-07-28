import SlidingBanner from "../components/SlidingBanner";
import Hashtag from "../components/Hashtag";
const Main = () => {
  return (
    <div className="w-screen h-full bg-customColor bg-opacity-20">
      <SlidingBanner />
      <div className="w-screen h-screen flex flex-col">
        <div className="p-3 text-s">
          도서 검색을 통해 원하는 교재를 검색해보세요!
        </div>
        <input className="bg-white-900 rounded-3xl mx-20 my-4 p-3" />
        <div className="m-3">
          <Hashtag />
        </div>
      </div>
    </div>
  );
};
export default Main;
