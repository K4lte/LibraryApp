package org.example.libraryapp.dao.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.model.Book;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements Dao<Book> {
    private final SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Book> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b JOIN FETCH b.author LEFT JOIN FETCH b.reader JOIN FETCH b.bookGenres", Book.class)
                .getResultList();
    }

    @Override
    public Optional<Book> getById(int id) {
        var bookModel = sessionFactory.getCurrentSession().getReference(Book.class, id);
        return Optional.ofNullable(bookModel);
    }

    @Override
    public Optional<Book> getByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE title = :text", Book.class)
                .setParameter("text", lookupText)
                .stream()
                .findFirst();
    }

    @Override
    public List<Book> getAllByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE title LIKE :text", Book.class)
                .setParameter("text", lookupText)
                .getResultList();
    }

    @Override
    public void update(Book book) {
        sessionFactory.getCurrentSession().merge(book);
    }

    @Override
    public void save(Book book) {
        sessionFactory.getCurrentSession().persist(book);
    }

    @Override
    public void delete(int id) {
        var session = sessionFactory.getCurrentSession();
        Book result = session.getReference(Book.class, id);
        if (null != result) {
            session.remove(result);
        }
    }


}

