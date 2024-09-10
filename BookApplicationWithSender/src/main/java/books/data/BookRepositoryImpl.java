package books.data;

import books.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Collection<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public Book save(Book book) {
        return books.put(book.getIsbn(), book);
    }

    @Override
    public void delete(String isbn) {
        books.remove(isbn);
    }
}
