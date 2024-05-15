package org.example.libraryapp.dao.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.model.Author;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements Dao<Author> {
    private final SessionFactory sessionFactory;

    public AuthorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Author> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Author", Author.class)
                .getResultList();
    }

    @Override
    public Optional<Author> getById(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().getReference(Author.class, id));
    }

    @Override
    public List<Author> getAllByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE name LIKE :text", Author.class)
                .setParameter("text", lookupText)
                .getResultList();
    }

    @Override
    public Optional<Author> getByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE name = :text", Author.class)
                .setParameter("text", lookupText)
                .stream()
                .findFirst();
    }

    @Override
    public void update(Author model) {
        sessionFactory.getCurrentSession().merge(model);
    }

    @Override
    public void save(Author model) {
        sessionFactory.getCurrentSession().persist(model);
    }

    @Override
    public void delete(int id) {
        var session = sessionFactory.getCurrentSession();
        var authorModel = session.getReference(Author.class, id);
        if (authorModel != null) {
            session.remove(authorModel);
        }
    }

}

