export interface Book {
  bookId: number;
  bookTitle: string;
  bookCategory: string;
  bookImage: string;
  bookUrl: string;
}

export interface BookData {
  bookList: Book[];
}
