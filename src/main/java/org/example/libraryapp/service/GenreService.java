package org.example.libraryapp.service;

import org.example.libraryapp.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService extends SimpleService<GenreDto> {
    List<GenreDto> getAllByName(String lookupText);
    Optional<GenreDto> getByName(String lookupText);
}
