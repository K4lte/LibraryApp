package org.example.libraryapp.dao.impl;

import org.example.libraryapp.dao.Dao;
import org.example.libraryapp.model.Reader;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReaderDaoImpl implements Dao<Reader> {
    private final SessionFactory sessionFactory;

    public ReaderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Reader> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Reader", Reader.class)
                .getResultList();
    }

    @Override
    public Optional<Reader> getById(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().getReference(Reader.class, id));
    }

    @Override
    public Optional<Reader> getByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE email = :text", Reader.class)
                .setParameter("text", lookupText)
                .stream()
                .findFirst();
    }

    @Override
    public List<Reader> getAllByText(String lookupText) {
        return sessionFactory.getCurrentSession()
                .createQuery("WHERE email LIKE :text", Reader.class)
                .setParameter("text", lookupText)
                .getResultList();
    }

    @Override
    public void update(Reader model) {
        sessionFactory.getCurrentSession().merge(model);
    }

    @Override
    public void save(Reader model) {
        sessionFactory.getCurrentSession().persist(model);
    }

    @Override
    public void delete(int id) {
        var session = sessionFactory.getCurrentSession();
        var readerModel = session.getReference(Reader.class, id);
        if (null != readerModel) {
            session.remove(readerModel);
        }
    }

}

