package com.esri.bank_accounts.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TransactionRepository implements CrudRepository<TransactionRecord, Long> {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TransactionRecord save(TransactionRecord entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception ex) {

        }
        return entity;
    }


    @Override
    @Transactional
    public <S extends TransactionRecord> Iterable<S> saveAll(Iterable<S> entities) {
        try {
            for(S entity : entities) {
                entityManager.persist(entity);
            }
        } catch (Exception ex) {

        }
        return entities;

    }

    @Override
    public Optional<TransactionRecord> findById(Long aLong) {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TransactionRecord> findAll() {
        return null;
    }

    @Override
    public Iterable<TransactionRecord> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(TransactionRecord entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TransactionRecord> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
