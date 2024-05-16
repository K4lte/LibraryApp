package org.example.libraryapp.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.libraryapp.dto.ReaderDto;
import org.example.libraryapp.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public List<ReaderDto> getAllReaders() {
        return readerService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ReaderDto getReaderById(@PathVariable("id") Integer id) {
        return readerService.getById(id);
    }

    @GetMapping(value = "/emails")
    public List<ReaderDto> getAllReadersByEmail(@RequestParam("email") @NotBlank String email) {
        return readerService.getAllByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveReader(@RequestBody ReaderDto dto) {
        readerService.save(dto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReader(@PathVariable("id") Integer id, @RequestBody ReaderDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        readerService.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteReader(@PathVariable("id") Integer id, @RequestBody ReaderDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        readerService.delete(dto.getId());
    }
}
