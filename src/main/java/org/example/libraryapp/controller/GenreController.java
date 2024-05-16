package org.example.libraryapp.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.libraryapp.dto.GenreDto;
import org.example.libraryapp.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAll();
    }

    @GetMapping(value = "/{id}")
    public GenreDto getGenreById(@PathVariable("id") Integer id) {
        return genreService.getById(id);
    }

    @GetMapping(value = "/names")
    public List<GenreDto> getAllGenresByName(@RequestParam("name") @NotBlank String name) {
        return genreService.getAllByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGenre(@RequestBody GenreDto dto) {
        genreService.save(dto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGenre(@PathVariable("id") Integer id, @RequestBody GenreDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        genreService.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGenre(@PathVariable("id") Integer id, @RequestBody GenreDto dto) {
        if (!Objects.equals(id, dto.getId())) {
            throw new IllegalArgumentException("IDs don't match");
        }
        genreService.delete(dto.getId());
    }
}
