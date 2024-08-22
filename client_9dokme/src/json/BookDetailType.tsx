export interface BookDetailType {
  bookId: number;
  publishDate: string;
  bookTitle: string;
  bookCategory: string;
  bookImage: string;
  author: string;
  description: string;
  isMarked: boolean;
}
export interface Books {
  books: BookDetailType[];
}
