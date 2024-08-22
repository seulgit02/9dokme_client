// const cors_proxy = require("cors-anywhere");

// const host = "localhost";
// const port = 8080;

// cors_proxy
//   .createServer({
//     originWhitelist: [], // 모든 출처 허용
//     requireHeader: ["origin", "x-requested-with"],
//     removeHeaders: ["cookie", "cookie2"],
//   })
//   .listen(port, host, function () {
//     console.log(`Running CORS Anywhere on ${host}:${port}`);
//   });
// const { createProxyMiddleware } = require("http-proxy-middleware");

// module.exports = function (app) {
//   app.use(
//     "/api",
//     createProxyMiddleware({
//       target: "http://localhost:8080",
//       changeOrigin: true,
//       withCredentials: true,
//     })
//   );
//   app.use(
//     "/ws",
//     createProxyMiddleware({
//       target: "http://localhost:8080",
//       ws: true,
//     })
//   );
// };
