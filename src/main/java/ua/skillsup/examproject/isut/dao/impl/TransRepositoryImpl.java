package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotImplementedDataAccessMethod;

import javax.persistence.EntityManager;

@Repository
public class TransRepositoryImpl implements TransRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    public TransRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(Transaction transaction) {
        if(transaction != null) {
            entityManager.persist(transaction);
        }
        return transaction.getId();
    }

    @Override
    public void update(Transaction transaction){}

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public Transaction getOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Transaction> findAll() {
        return entityManager.createQuery("from Transaction", Transaction.class).getResultList();
    }
}