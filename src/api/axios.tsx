import axios from "axios";
const API = axios.create({
  baseURL: "https://www.9dokme.p-e.kr",

  headers: {
    "Content-Type": "application/json",
  },
});

export default API;
