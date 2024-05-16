package org.example.libraryapp.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll();
    Optional<T> getById(int id);
    Optional<T> getByText(String lookupText);
    List<T> getAllByText(String lookupText);
    void update(T t);
    void save(T t);
    void delete(int id);
}
