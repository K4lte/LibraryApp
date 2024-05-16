package org.example.libraryapp.service.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.dto.ReaderDto;
import org.example.libraryapp.model.Reader;
import org.example.libraryapp.service.ReaderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Qualifier("readerDaoImpl")
    private final Dao<Reader> dao;
    private final ModelMapper modelMapper;

    public ReaderServiceImpl(Dao<Reader> dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<ReaderDto> getAll() {
        return dao.getAll().stream()
                .map(model -> modelMapper.map(model, ReaderDto.class))
                .toList();
    }

    @Override
    @Transactional
    public ReaderDto getById(int id) {
        return modelMapper.map(dao.getById(id), ReaderDto.class);
    }

    @Override
    @Transactional
    public List<ReaderDto> getAllByEmail(String lookupText) {
        return dao.getAllByText(lookupText).stream()
                .map(model -> modelMapper.map(model, ReaderDto.class))
                .toList();
    }

    @Override
    public Optional<ReaderDto> getByEmail(String lookupText) {
        var reader = dao.getByText(lookupText);
        return reader.isPresent()
                ? Optional.of(modelMapper.map(reader, ReaderDto.class))
                : Optional.empty();
    }

    @Override
    @Transactional
    public void update(ReaderDto dto) {
        dao.update(modelMapper.map(dto, Reader.class));
    }

    @Override
    @Transactional
    public void save(ReaderDto dto) {
        dao.save(modelMapper.map(dto, Reader.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

}
