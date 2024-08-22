// import React, { useState, useEffect, useRef } from "react";
// import { useParams, useLocation } from "react-router-dom";
// import styled from "styled-components";
// import * as pdfjsLib from "pdfjs-dist";
// import CommunityTab from "../components/CommunityTab";
// import BookmarkSuccess from "../components/BookmarkSuccess";
// import { Progress } from "../components/ui/progress";
// import back from "../components/images/back.png";
// pdfjsLib.GlobalWorkerOptions.workerSrc = "/pdf.worker.min.mjs";

// interface Book {
//   bookTitle: string;
//   author: string;
//   bookCategory: string;
//   bookURL: string;
// }

// const PdfViewer: React.FC = () => {
//   const [page, setPage] = useState<number>(() =>
//     parseInt(localStorage.getItem("lastPage") || "1")
//   );
//   const [pdfDoc, setPdfDoc] = useState<pdfjsLib.PDFDocumentProxy | null>(null);
//   const [numPages, setNumPages] = useState<number>(0);
//   const { bookId } = useParams<{ bookId: string }>();
//   const location = useLocation();
//   const { book }: { book?: Book } = location.state || {};
//   const canvasRef = useRef<HTMLCanvasElement | null>(null);

//   //모달창
//   const [isOpen, setIsOpen] = useState<boolean>(false);
//   const handleModalOn = () => {
//     setIsOpen(true);
//   };
//   const handleModalOff = () => {
//     setIsOpen(false);
//   };

//   useEffect(() => {
//     const loadPdf = async () => {
//       if (book?.bookURL) {
//         const proxyUrl = "http://localhost:8080/";
//         const pdfUrl = `${proxyUrl}${book.bookURL}`;
//         try {
//           const loadedPdfDoc = await pdfjsLib.getDocument(pdfUrl).promise;
//           setPdfDoc(loadedPdfDoc);
//           setNumPages(loadedPdfDoc.numPages);
//         } catch (error) {
//           console.error("Error loading PDF document:", error);
//         }
//       }
//     };

//     loadPdf();
//   }, [book?.bookURL]);

//   useEffect(() => {
//     localStorage.setItem("lastPage", page.toString());
//   }, [page]);

//   const handlePreviousPage = () => {
//     setPage((prevPage) => Math.max(prevPage - 1, 1));
//   };

//   useEffect(() => {
//     const lastPage = localStorage.getItem("lastPage");
//     console.log("마지막으로 읽은 페이지:", lastPage);
//   }, []);

//   useEffect(() => {
//     const renderPage = async (pageNum: number) => {
//       if (pdfDoc && canvasRef.current) {
//         try {
//           const page = await pdfDoc.getPage(pageNum);
//           const scale = 1;
//           const viewport = page.getViewport({ scale });
//           const context = canvasRef.current.getContext("2d");

//           if (context) {
//             canvasRef.current.height = viewport.height;
//             canvasRef.current.width = viewport.width;

//             const renderContext = {
//               canvasContext: context,
//               viewport,
//             };
//             await page.render(renderContext).promise;
//           }
//         } catch (error) {
//           console.error("Error rendering page:", error);
//         }
//       }
//     };

//     renderPage(page);
//   }, [pdfDoc, page]);

//   const handleNextPage = () => {
//     setPage((prevPage) => Math.min(prevPage + 1, numPages));
//   };

//   const handlePageInput = (e: React.ChangeEvent<HTMLInputElement>) => {
//     const newPage = parseInt(e.target.value, 10);
//     if (!isNaN(newPage)) {
//       setPage(Math.min(Math.max(newPage, 1), numPages));
//     }
//   };

//   const handlePageBlur = () => {
//     // 입력란에서 포커스를 잃었을 때 범위를 벗어난 입력을 1과 numPages 사이로 조정합니다.
//     setPage((currentPage) => Math.min(Math.max(currentPage, 1), numPages));
//   };

//   return (
//     <Frame className="bg-customGradient w-full h-[150vh]">
//       <CommunityTab book={book} />

//       <div className="flex flex-col items-center justify-center">
//         <div className="text-center mt-20 mb-5">
//           <div className="font-bold text-[1.5vw]">[{book?.bookTitle}]</div>
//           <p className="text-[1.2vw] mt-[0.2vw]">{book?.author}</p>
//         </div>
//         <div>
//           <TagBtn>{book?.bookCategory}</TagBtn>
//         </div>

//         <div
//           className="mt-10"
//           style={{ height: "750px", width: "100%", overflow: "auto" }}
//         >
//           <div className="flex justify-center items-center h-full">
//             <canvas ref={canvasRef}></canvas>
//           </div>
//         </div>

//         <div className="mt-5 flex justify-center">
//           <button
//             className="bg-blue-500 text-white font-bold py-2 px-4 rounded mx-2"
//             onClick={handlePreviousPage}
//             disabled={page === 1}
//           >
//             이전페이지
//           </button>
//           <input
//             type="number"
//             className="text-center w-20"
//             value={page}
//             onChange={handlePageInput}
//             onBlur={handlePageBlur}
//           />
//           <button
//             className="bg-blue-500 text-white font-bold py-2 px-4 rounded mx-2"
//             onClick={handleNextPage}
//             disabled={page === numPages}
//           >
//             다음페이지
//           </button>
//         </div>
//       </div>
//     </Frame>
//   );
// };

// const Frame = styled.div`
//   min-height: 100vh;
//   background: radial-gradient(
//     circle at 10% 50%,
//     rgb(163, 175, 243) 0%,
//     rgb(220, 182, 232) 100.2%
//   );
//   padding: 20px;
//   overflow: auto;
// `;

// const TagBtn = styled.button`
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   text-align: center;
//   width: auto;
//   height: 2vw;
//   border-radius: 3vw;
//   border-style: solid;
//   border-width: 1px;
//   border-color: #2519b2;
//   padding: 1vw;
//   font-size: 1vw;
//   background-color: #d195ff;
// `;

// export default PdfViewer;
import React, { useState, useEffect, useRef } from "react";
import { useParams, useLocation, useNavigate } from "react-router-dom";
import styled from "styled-components";
import * as pdfjsLib from "pdfjs-dist";
import CommunityTab from "../components/CommunityTab";
import BookmarkSuccess from "../components/BookmarkSuccess";
import { Progress } from "../components/ui/progress";
import back from "../images/back.png";
pdfjsLib.GlobalWorkerOptions.workerSrc = "/pdf.worker.min.mjs";

interface Book {
  bookTitle: string;
  author: string;
  bookCategory: string;
  bookURL: string;
}

const PdfViewer: React.FC = () => {
  const [page, setPage] = useState<number>(() =>
    parseInt(localStorage.getItem("lastPage") || "1")
  );
  const [pdfDoc, setPdfDoc] = useState<pdfjsLib.PDFDocumentProxy | null>(null);
  const [numPages, setNumPages] = useState<number>(0);
  const { bookId } = useParams<{ bookId: string }>();
  const location = useLocation();
  const { book }: { book?: Book } = location.state || {};
  const canvasRef = useRef<HTMLCanvasElement | null>(null);
  const navigate = useNavigate();

  // 모달창
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const handleModalOn = () => {
    setIsOpen(true);
  };
  const handleModalOff = () => {
    setIsOpen(false);
  };

  useEffect(() => {
    const loadPdf = async () => {
      if (book?.bookURL) {
        const proxyUrl = "http://localhost:8080/";
        const pdfUrl = `${proxyUrl}${book.bookURL}`;
        try {
          const loadedPdfDoc = await pdfjsLib.getDocument(pdfUrl).promise;
          setPdfDoc(loadedPdfDoc);
          setNumPages(loadedPdfDoc.numPages);
        } catch (error) {
          console.error("Error loading PDF document:", error);
        }
      }
    };

    loadPdf();
  }, [book?.bookURL]);

  useEffect(() => {
    localStorage.setItem("lastPage", page.toString());
  }, [page]);

  const handlePreviousPage = () => {
    setPage((prevPage) => Math.max(prevPage - 1, 1));
  };

  useEffect(() => {
    const lastPage = localStorage.getItem("lastPage");
    console.log("마지막으로 읽은 페이지:", lastPage);
  }, []);

  useEffect(() => {
    const renderPage = async (pageNum: number) => {
      if (pdfDoc && canvasRef.current) {
        try {
          const page = await pdfDoc.getPage(pageNum);
          const scale = 1;
          const viewport = page.getViewport({ scale });
          const context = canvasRef.current.getContext("2d");

          if (context) {
            canvasRef.current.height = viewport.height;
            canvasRef.current.width = viewport.width;

            const renderContext = {
              canvasContext: context,
              viewport,
            };
            await page.render(renderContext).promise;
          }
        } catch (error) {
          console.error("Error rendering page:", error);
        }
      }
    };

    renderPage(page);
  }, [pdfDoc, page]);

  const handleNextPage = () => {
    setPage((prevPage) => Math.min(prevPage + 1, numPages));
  };

  const handlePageInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newPage = parseInt(e.target.value, 10);
    if (!isNaN(newPage)) {
      setPage(Math.min(Math.max(newPage, 1), numPages));
    }
  };

  const handlePageBlur = () => {
    setPage((currentPage) => Math.min(Math.max(currentPage, 1), numPages));
  };

  const handleBackClick = () => {
    localStorage.setItem(`lastPage-${bookId}`, page.toString());
    navigate(-1); // 이전 페이지로 이동
  };

  const progress = (page / numPages) * 100;

  return (
    <Frame className="bg-customGradient w-full h-[150vh]">
      <CommunityTab book={book} />

      <BackButton onClick={handleBackClick} className="w-[4vw] h-[1vw]">
        <img src={back} alt="뒤로가기" />
      </BackButton>

      <div className="flex flex-col items-center justify-center">
        <div className="text-center mt-20 mb-5">
          <div className="font-bold text-[1.5vw]">[{book?.bookTitle}]</div>
          <p className="text-[1.2vw] mt-[0.2vw]">{book?.author}</p>
        </div>
        <div>
          <TagBtn>{book?.bookCategory}</TagBtn>
        </div>

        <div
          className="mt-10"
          style={{ height: "750px", width: "100%", overflow: "auto" }}
        >
          <div className="flex justify-center items-center h-full">
            <canvas ref={canvasRef}></canvas>
          </div>
        </div>

        <div className="mt-5 flex justify-center">
          <button
            className="bg-blue-500 text-white font-bold py-2 px-4 rounded mx-2"
            onClick={handlePreviousPage}
            disabled={page === 1}
          >
            이전페이지
          </button>
          <input
            type="number"
            className="text-center w-20"
            value={page}
            onChange={handlePageInput}
            onBlur={handlePageBlur}
          />
          <button
            className="bg-blue-500 text-white font-bold py-2 px-4 rounded mx-2"
            onClick={handleNextPage}
            disabled={page === numPages}
          >
            다음페이지
          </button>
        </div>

        <div className="w-full mt-5">
          <Progress value={progress} />
        </div>
      </div>
    </Frame>
  );
};

const Frame = styled.div`
  min-height: 100vh;
  background: radial-gradient(
    circle at 10% 50%,
    rgb(163, 175, 243) 0%,
    rgb(220, 182, 232) 100.2%
  );
  padding: 20px;
  overflow: auto;
`;

const TagBtn = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  width: auto;
  height: 2vw;
  border-radius: 3vw;
  border-style: solid;
  border-width: 1px;
  border-color: #2519b2;
  padding: 1vw;
  font-size: 1vw;
  background-color: #d195ff;
`;

const BackButton = styled.button`
  position: fixed;
  top: 20px;
  left: 20px;
  background: none;
  border: none;
  cursor: pointer;
  z-index: 1000;

  img {
    width: 50px;
    height: 50px;
  }
`;

export default PdfViewer;
