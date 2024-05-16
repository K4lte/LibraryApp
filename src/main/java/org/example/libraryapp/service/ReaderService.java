package org.example.libraryapp.service;

import org.example.libraryapp.dto.ReaderDto;

import java.util.List;
import java.util.Optional;

public interface ReaderService extends SimpleService<ReaderDto> {
    List<ReaderDto> getAllByEmail(String lookupText);
    Optional<ReaderDto> getByEmail(String lookupText);
}
