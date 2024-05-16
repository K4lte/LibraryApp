package org.example.libraryapp.service;

import java.util.List;

public interface SimpleService<T> {
    List<T> getAll();
    T getById(int id);
    void update(T t);
    void save(T t);
    void delete(int id);
}
