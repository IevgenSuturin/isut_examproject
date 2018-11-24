package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.TrTypeRepository;
import ua.skillsup.examproject.isut.dao.entity.TransTypes;

import javax.persistence.EntityManager;

@Repository
public class TrTypeRepositoryImpl implements TrTypeRepository {
    private final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public TrTypeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(TransTypes transTypes) {
        if(transTypes.getId()==0){
            entityManager.persist(transTypes);
        }
        return transTypes.getId();
    }

    @Override
    public void update(TransTypes transTypes) {
        if(transTypes.getId() != 0){
            entityManager.merge(transTypes);
        }
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public TransTypes getOne(Long aLong) {
        return entityManager.find(TransTypes.class, aLong);
    }

    @Override
    public Iterable<TransTypes> findAll() {
        return entityManager.createQuery("from TransTypes", TransTypes.class).getResultList();
    }
}
