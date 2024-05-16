package org.example.libraryapp.service;

import org.example.libraryapp.dto.BookDto;

import java.util.List;

public interface BookService extends SimpleService<BookDto> {
    List<BookDto> getByTitle(String lookupText);
}
