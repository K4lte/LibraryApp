package org.example.libraryapp.dao.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.model.Genre;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreDaoImpl implements Dao<Genre> {
    private final SessionFactory sessionFactory;

    public GenreDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Genre> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Genre", Genre.class)
                .getResultList();
    }

    @Override
    public Optional<Genre> getById(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().getReference(Genre.class, id));
    }

    @Override
    public List<Genre> getAllByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE name LIKE :text", Genre.class)
                .setParameter("text", lookupText)
                .getResultList();
    }

    @Override
    public Optional<Genre> getByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE name = :text", Genre.class)
                .setParameter("text", lookupText)
                .stream()
                .findFirst();
    }

    @Override
    public void update(Genre model) {
        sessionFactory.getCurrentSession().merge(model);
    }

    @Override
    public void save(Genre model) {
        sessionFactory.getCurrentSession().persist(model);
    }

    @Override
    public void delete(int id) {
        var session = sessionFactory.getCurrentSession();
        var genreModel = session.getReference(Genre.class, id);
        if (genreModel != null) {
            session.remove(genreModel);
        }
    }

}

