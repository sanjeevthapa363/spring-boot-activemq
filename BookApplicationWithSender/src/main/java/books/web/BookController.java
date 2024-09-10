package books.web;

import books.domain.Book;
import books.service.BookService;
import books.web.dto.BooksDto;
import books.web.dto.CustomErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                service.addBook(book)
        );
    }

    @PutMapping("{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        if (service.getBook(isbn).isEmpty())
            return CustomErrorDto.notFound("Book with isbn " + isbn);
        return ResponseEntity.ok(service.updateBook(isbn, book));
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        if (service.getBook(isbn).isEmpty())
            return CustomErrorDto.notFound("Book with isbn " + isbn);
        service.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        return ResponseEntity.of(service.getBook(isbn));
    }

    @GetMapping
    public ResponseEntity<BooksDto> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

}
