export interface Book {
  bookId: number;
  bookTitle: string;
  bookCategory: string;
  bookImage: string;
  bookUrl: string;
  isMarked: boolean;
}

export interface BookData {
  bookData: Book[];
}
