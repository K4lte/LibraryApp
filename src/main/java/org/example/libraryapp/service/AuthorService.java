package org.example.libraryapp.service;

import org.example.libraryapp.dto.AuthorDto;

import java.util.List;

public interface AuthorService extends SimpleService<AuthorDto> {
    List<AuthorDto> getByName(String lookupText);
}
