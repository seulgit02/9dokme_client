export interface BookDetailType {
  bookId: number;
  publishDate: string;
  bookTitle: string;
  bookCategory: string;
  bookImage: string;
  author: string;
  description: string;
}
export interface Books {
  books: BookDetailType[];
}
