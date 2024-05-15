package org.example.libraryapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BookDto implements Serializable {
    private int id;
    private String title;
    private Set<GenreDto> genres;
    private AuthorDto author;
    private ReaderDto reader;
}