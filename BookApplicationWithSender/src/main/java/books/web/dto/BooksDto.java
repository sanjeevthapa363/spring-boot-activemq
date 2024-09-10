package books.web.dto;

import books.domain.Book;

import java.util.Collection;

public record BooksDto (
        Collection<Book> books
) {}
