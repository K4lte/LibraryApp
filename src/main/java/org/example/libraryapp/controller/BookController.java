package org.example.libraryapp.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.libraryapp.dto.BookDto;
import org.example.libraryapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping(value = "/{id}")
    public BookDto getBookById(@PathVariable("id") Integer id) {
        return bookService.getById(id);
    }

    @GetMapping(value = "/titles")
    public List<BookDto> getBookByTitle(@RequestParam("title") @NotBlank String title) {
        return bookService.getByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook(@RequestBody BookDto dto) {
        bookService.save(dto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable("id") Integer id, @RequestBody BookDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        bookService.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Integer id, @RequestBody BookDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        bookService.delete(dto.getId());
    }
}

