const Hashtag = () => {
  const categories = ["공학", "자연", "예술", "인문/사회", "체육", "경영/경제"];
  return (
    <div className="grid grid-cols-6 gap-4">
      {categories.map(function (category) {
        return (
          <div
            className="rounded-2xl text-center text-xs p-1"
            style={{
              borderColor: "#2519B2",
              borderWidth: "1px",
            }}
            key={category}
          >
            #{category}
          </div>
        );
      })}
    </div>
  );
};

export default Hashtag;
