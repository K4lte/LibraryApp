package org.example.libraryapp.service.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.dto.GenreDto;
import org.example.libraryapp.model.Genre;
import org.example.libraryapp.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Qualifier("genreDaoImpl")
    private final Dao<Genre> dao;
    private final ModelMapper modelMapper;

    public GenreServiceImpl(Dao<Genre> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<GenreDto> getAll() {
        return dao.getAll().stream()
                .map(model -> modelMapper.map(model, GenreDto.class))
                .toList();
    }

    @Override
    @Transactional
    public GenreDto getById(int id) {
        return modelMapper.map(dao.getById(id), GenreDto.class);
    }

    @Override
    @Transactional
    public List<GenreDto> getAllByName(String lookupText) {
        return dao.getAllByText(lookupText).stream()
                .map(model -> modelMapper.map(model, GenreDto.class))
                .toList();
    }

    @Override
    public Optional<GenreDto> getByName(String lookupText) {
        var genre = dao.getByText(lookupText);
        return genre.isPresent()
                ? Optional.of(modelMapper.map(genre, GenreDto.class))
                : Optional.empty();
    }

    @Override
    @Transactional
    public void update(GenreDto dto) {
        dao.update(modelMapper.map(dto, Genre.class));
    }

    @Override
    @Transactional
    public void save(GenreDto dto) {
        dao.save(modelMapper.map(dto, Genre.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

}
