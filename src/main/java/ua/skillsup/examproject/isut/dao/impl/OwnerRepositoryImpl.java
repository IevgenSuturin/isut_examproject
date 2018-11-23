package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(Owner owner) {
        if(owner.getId() == 0){
            entityManager.persist(owner);
        }else {
            entityManager.merge(owner);
        }
        return  owner.getId();
    }

    @Override
    public void update(Owner owner) {

    }

    @Override
    public Owner getOne(long id) {
        return null;
    }

    @Override
    public boolean delete(long ownerId) {
        Owner owner = entityManager.find(Owner.class, ownerId);
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
