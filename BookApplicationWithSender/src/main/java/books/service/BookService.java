package books.service;

import books.domain.Book;
import books.web.dto.BooksDto;

import java.util.Optional;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(String isbn, Book book);

    void deleteBook(String isbn);

    Optional<Book> getBook(String isbn);

    BooksDto getAllBooks();

}
