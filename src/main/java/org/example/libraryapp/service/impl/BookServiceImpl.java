package org.example.libraryapp.service.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.dto.BookDto;
import org.example.libraryapp.model.Book;
import org.example.libraryapp.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Qualifier("bookDaoImpl")
    private Dao<Book> bookDao;
    private final ModelMapper modelMapper;

    public BookServiceImpl(Dao<Book> bookDao, ModelMapper modelMapper) {
        this.bookDao = bookDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<BookDto> getAll() {
        return bookDao.getAll().stream()
                .map(model -> modelMapper.map(model, BookDto.class))
                .toList();
    }

    @Override
    @Transactional
    public BookDto getById(int id) {
        return modelMapper.map(bookDao.getById(id), BookDto.class);
    }

    @Override
    @Transactional
    public List<BookDto> getByTitle(String lookupText) {
        return bookDao.getAllByText(lookupText).stream()
                .map(model -> modelMapper.map(model, BookDto.class))
                .toList();
    }

    @Override
    @Transactional
    public void update(BookDto dto) {
        bookDao.update(modelMapper.map(dto, Book.class));

    }

    @Override
    @Transactional
    public void save(BookDto dto) {
        bookDao.save(modelMapper.map(dto, Book.class));
    }

    @Override
    @Transactional
    public void delete(int id) {
        bookDao.delete(id);
    }

}
