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
    public long createTrType(TransTypes transTypes) {
        if(transTypes.getId()==0){
            entityManager.persist(transTypes);
        }
        return transTypes.getId();
    }

    @Override
    public Iterable<TransTypes> getAll() {
        return entityManager.createQuery("from TransTypes", TransTypes.class).getResultList();
    }

    @Override
    public TransTypes getTrTypeById(long id) {
        return entityManager.find(TransTypes.class, id);
    }
}
