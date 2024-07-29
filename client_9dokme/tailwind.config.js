module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      fontSize: {
        "vw-1": "1vw",
        "vw-2": "2vw",
      },
      colors: {
        customColor: "#C5B5F7",
      },
      opacity: {
        20: "0.2",
      },
    },
  },
  variants: {},
  plugins: [require("@tailwindcss/aspect-ratio")],
};
