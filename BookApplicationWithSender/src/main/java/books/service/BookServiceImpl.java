package books.service;

import books.data.BookRepository;
import books.domain.Book;
import books.integration.JmsSender;
import books.web.dto.BooksDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final JmsSender jmsSender;

    public BookServiceImpl(BookRepository repository, JmsSender jmsSender) {
        this.repository = repository;
        this.jmsSender = jmsSender;
    }

    public Book addBook(Book book) {
        jmsSender.sendMessage(book);
        return repository.save(book);
    }

    public Book updateBook(String isbn, Book book) {
        book.setIsbn(isbn);
        jmsSender.sendMessage(book);
        return repository.save(book);
    }

    public void deleteBook(String isbn) {
        jmsSender.sendMessage(repository.findByIsbn(isbn).orElse(null));
        repository.delete(isbn);
    }

    public Optional<Book> getBook(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public BooksDto getAllBooks() {
        return new BooksDto(repository.findAll());
    }
}
