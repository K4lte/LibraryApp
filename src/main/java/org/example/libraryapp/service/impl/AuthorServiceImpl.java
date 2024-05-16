package org.example.libraryapp.service.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.dto.AuthorDto;
import org.example.libraryapp.model.Author;
import org.example.libraryapp.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Qualifier("authorDaoImpl")
    private final Dao<Author> dao;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(Dao<Author> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<AuthorDto> getAll() {
        return dao.getAll().stream()
                .map(model -> modelMapper.map(model, AuthorDto.class))
                .toList();
    }

    @Override
    @Transactional
    public AuthorDto getById(int id) {
        return modelMapper.map(dao.getById(id), AuthorDto.class);
    }

    @Override
    @Transactional
    public List<AuthorDto> getByName(String lookupText) {
        return dao.getAllByText(lookupText).stream()
                .map(model -> modelMapper.map(model, AuthorDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void update(AuthorDto dto) {
        dao.update(modelMapper.map(dto, Author.class));
    }

    @Override
    @Transactional
    public void save(AuthorDto dto) {
        dao.save(modelMapper.map(dto, Author.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

}
