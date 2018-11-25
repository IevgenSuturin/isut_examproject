package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Account;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class AccountRepsitoryImpl implements AccountRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    @Autowired
    public AccountRepsitoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(Account account) {
        if(account != null) {
            entityManager.persist(account);
        }
        return account.getId();
    }

    @Override
    public void update(Account account)  {
        if(account.getId() != 0){
            entityManager.merge(account);
        }
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public Account getOne(Long aLong) {
        return entityManager.find(Account.class, aLong);
    }

    @Override
    public Iterable<Account> findAll(){
        return null;
    }

    @Override
    public Account getAccountByOwnerAndItem(Owner owner, Item item) {
        try{
            return  entityManager.createQuery("from Account where item=:item and owner=:owner", Account.class).
                    setParameter("item", item).
                    setParameter("owner", owner).getSingleResult();
        }catch (NoResultException ex){
            return null;
        }
    }
}
