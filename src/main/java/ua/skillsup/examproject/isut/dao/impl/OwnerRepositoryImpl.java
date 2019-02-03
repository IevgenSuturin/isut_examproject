package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Owner;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    @Autowired
    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long create(Owner owner) {
        if(owner.getId() == null){
            entityManager.persist(owner);
        }
        return  owner.getId();
    }

    @Override
    public void update(Owner owner) {
        if(owner.getId() != null){
            entityManager.merge(owner);
        }
    }

    @Override
    public Owner getOne(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public boolean exist(Long aLong) {
        return getOne(aLong) != null;
    }

    @Override
    public boolean delete(Owner owner) {
        if(owner != null) {
            entityManager.remove(owner);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Owner> findAll() {
        return entityManager.createQuery("from Owner", Owner.class).getResultList();
    }

    @Override
    public Optional<Owner> findByFirstName(String fname) {
        return Optional.empty();
    }


}
