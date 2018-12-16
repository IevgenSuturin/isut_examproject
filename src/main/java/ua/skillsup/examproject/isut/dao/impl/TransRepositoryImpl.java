package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.controller.dto.TransInfoDto;
import ua.skillsup.examproject.isut.dao.OwnerRepository;
import ua.skillsup.examproject.isut.dao.TransRepository;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class TransRepositoryImpl implements TransRepository {
    private final Logger logger = LoggerFactory.getLogger(OwnerRepository.class);

    private final EntityManager entityManager;

    public TransRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Long create(Transaction transaction) {
        if(transaction.getId() == null) {
            transaction.setDate_stor(LocalDateTime.now());
            entityManager.persist(transaction);
        }
        return transaction.getId();
    }

    @Override
    public void update(Transaction transaction){}

    @Override
    public boolean delete(Transaction transaction) {
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

    @Override
    public void deleteOwnerTransactions(Owner owner) {
        entityManager.createQuery("DELETE from Transaction t where t.owner = :owner").
               setParameter("owner", owner).executeUpdate();
    }

    @Override
    public TransInfoDto getStatisticForPeriod(LocalDate dateStart, boolean stored) {
        Object[] resQuery;
        TransInfoDto result = new TransInfoDto(0, 0, "Unknown transaction");
        String desc;
        if(stored) {
            resQuery = (Object[]) entityManager.createQuery("select sum(t.count * i.price), sum(t.count) " +
                    " from Transaction t join t.item i " +
                    " where (t.date_stor between :dateStart and :dateStop) and t.count > 0")
                    .setParameter("dateStart", dateStart.atStartOfDay())
                    .setParameter("dateStop", LocalDateTime.now())
                    .getSingleResult();
            result.setDescription("Stored transactions");
        }else{
            resQuery = (Object[]) entityManager.createQuery("select sum(t.count * i.price), sum(t.count) " +
                    " from Transaction t join t.item i " +
                    " where (t.date_stor between :dateStart and :dateStop) and t.count < 0")
                    .setParameter("dateStart", dateStart.atStartOfDay())
                    .setParameter("dateStop", LocalDateTime.now())
                    .getSingleResult();
            result.setDescription("Withdrawed transactions");
        }

        if(resQuery[0] != null )
        {
            result.setAmount((Long)resQuery[0]);
        }
        if(resQuery[1]!= null)
        {
            result.setCount((Long)resQuery[1]);
        }
        return result;
    }
}
