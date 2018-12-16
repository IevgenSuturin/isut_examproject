package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.controller.dto.OwnerWithTotalPriceDto;
import ua.skillsup.examproject.isut.dao.AccountRepository;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.entity.Account;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AccountRepsitoryImpl implements AccountRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    @Autowired
    public AccountRepsitoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long create(Account account) {
        if(account.getId() == null) {
            entityManager.persist(account);
        }
        return account.getId();
    }

    @Override
    public void update(Account account)  {
        if(account.getId() != null){
            entityManager.merge(account);
        }
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }

    @Override
    public Account getOne(Long aLong) {
        return entityManager.find(Account.class, aLong);
    }

    @Override
    public Iterable<Account> findAll(){
        return entityManager.createQuery("from Account", Account.class).getResultList();
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

    @Override
    public boolean isActiveOwnerAccountExists(Owner owner) {
        List<Account> list;
        try{
            list = entityManager.createQuery("from Account a where a.count > :number  and a.owner=:owner", Account.class).
                    setParameter("number", 0L).
                    setParameter("owner", owner).getResultList();
        }catch (NoResultException ex) {
            return false;
        }
        return list.size()>0;
    }

    @Override
    public void deleteOwnerAccounts(Owner owner) {
        entityManager.createQuery("DELETE from Account a where a.owner = :owner").
                setParameter("owner",owner  ).executeUpdate();
    }

    @Override
    public Iterable<Owner> getAllActiveOwners() {
        List<Owner> list = new ArrayList<>();
        try{
            list = entityManager.createQuery("select distinct owner from Account a where a.count > :number ", Owner.class)
                    .setParameter("number", Long.valueOf(0)).getResultList()
                    .stream().distinct().collect(Collectors.toList());
        }catch (NoResultException ex) {
            return null;
        }

        return list;
    }

    @Override
    public Iterable<OwnerWithTotalPriceDto> getMostActiveOwners() {
        List<Object[]> list;
        List<OwnerWithTotalPriceDto> result = new ArrayList<>();

        list = entityManager.createQuery("select a.owner, sum(a.count * i.price) from Account a join a.item i " +
                "group by a.owner order by sum(a.count * i.price) desc" ).setMaxResults(5).getResultList();

        Iterator<Object[]> iterator = list.iterator();
        OwnerWithTotalPriceDto ownerWithTotalPriceDto;
        while (iterator.hasNext()){
            Object[] objects = iterator.next();
            if(objects[0]!=null && objects[1]!=null) {
                ownerWithTotalPriceDto = new OwnerWithTotalPriceDto((Owner) objects[0], (Long)objects[1]);
                result.add(ownerWithTotalPriceDto);
            }
        }
        return result;
    }
}
