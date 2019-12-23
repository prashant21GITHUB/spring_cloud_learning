package com.esri.bank_accounts.dao;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionRepository {



    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TransactionRecord save(TransactionRecord entity) {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to create new transaction record. Error: " + ex.getMessage());
        }
    }


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

    public Optional<TransactionRecord> findById(Long transactionId) {
        TypedQuery<TransactionRecord> namedQuery = entityManager
                .createNamedQuery("TransactionRecord.findById", TransactionRecord.class);
        namedQuery.setParameter("record.transactionId", transactionId);
        TransactionRecord obj = namedQuery.getSingleResult();
        if(obj != null) return Optional.of(obj);
        return Optional.empty();
    }

    public List<TransactionRecord> findByDate(String transactionDate) {
        TypedQuery<TransactionRecord> namedQuery = entityManager
                .createNamedQuery("TransactionRecord.findByTransactionDate", TransactionRecord.class);
        namedQuery.setParameter("transactionDate", transactionDate);
        List<TransactionRecord> resultList = namedQuery.getResultList();
        return resultList;
    }


    public List<TransactionRecord> findAll() {
        List<TransactionRecord> resultList = entityManager
                .createNamedQuery("TransactionRecord.findAll", TransactionRecord.class).getResultList();
        return resultList;
    }

    public List<TransactionRecord> findAllByPages(int pageNumber, int pageSize) {
        Query query = entityManager.createNamedQuery("TransactionRecord.findAll", TransactionRecord.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        List <TransactionRecord> resultList = query.getResultList();
        return resultList;
    }

    public Iterable<TransactionRecord> findAllById(Iterable<Long> longs) {
        return null;
    }

    public long count() {
        return 0;
    }

    @Transactional
    public void deleteById(Long transactionId) {
        TransactionRecord entity = new TransactionRecord();
        entity.setTransactionId(transactionId);
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete transaction record with ID: "+ entity.getTransactionId()+
                    ",  Error: " + ex.getMessage());
        }
    }

    @Transactional
    public void delete(TransactionRecord entity) {
        try {
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete transaction record with ID: "+ entity.getTransactionId()+
                    ",  Error: " + ex.getMessage());
        }
    }

    @Transactional
    public void update(Long transactionId, TransactionRecord transactionRecord) {
        TypedQuery<TransactionRecord> namedQuery = entityManager
                .createNamedQuery("TransactionRecord.findById", TransactionRecord.class);
        namedQuery.setParameter("transactionId", transactionId);
        TransactionRecord savedObject = namedQuery.getSingleResult();
        if(savedObject == null) {
            throw new RuntimeException("Record does not exists with transaction Id: "+ transactionId);
        }
        mapFields(transactionRecord, savedObject);
        try {
            entityManager.persist(savedObject);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update transaction record with ID: "+ transactionId +
                    ",  Error: " + ex.getMessage());
        }
    }

    private void mapFields(TransactionRecord source, TransactionRecord target) {
        if(source.getTransactionId() != null) target.setTransactionId(source.getTransactionId());
        if(source.getAccountNumber() != null) target.setAccountNumber(source.getAccountNumber());
        if(source.getAmount() != null) target.setAmount(source.getAmount());
        if(source.getBalanceAmount() != null) target.setBalanceAmount(source.getBalanceAmount());
        if(source.getDetails() != null) target.setDetails(source.getDetails());
        if(source.getTransactionType() != null) target.setTransactionType(source.getTransactionType());
        if(source.getValueDate() != null) target.setValueDate(source.getValueDate());
        if(source.getTransactionDate() != null) target.setTransactionDate(source.getTransactionDate());
    }
}
