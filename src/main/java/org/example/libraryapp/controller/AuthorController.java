package org.example.libraryapp.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.libraryapp.dto.AuthorDto;
import org.example.libraryapp.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping(value = "/{id}")
    public AuthorDto getGenreById(@PathVariable("id") Integer id) {
        return authorService.getById(id);
    }

    @GetMapping(value = "/names")
    public List<AuthorDto> getAuthorByName(@RequestParam("name") @NotBlank String name) {
        return authorService.getByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAuthor(@RequestBody AuthorDto dto) {
        authorService.save(dto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        authorService.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAuthor(@PathVariable("id") Integer id, @RequestBody AuthorDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        authorService.delete(dto.getId());
    }
}
