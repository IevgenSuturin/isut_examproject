package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    private final EntityManager entityManager;

    @Transactional
    @Override
    public boolean delete(long id) {
         Item item = entityManager.find(Item.class, id);
         if(item != null) {
             entityManager.remove(item);
             return true;
         }
         return false;
   }

    @Autowired
    public ItemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(Item item) {
        if(item.getId() == 0){
            entityManager.persist(item);
        }else {
            entityManager.merge(item);
        }
        return  item.getId();
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public Item getOne(long id) {
        return null;
    }

    @Override
    public Iterable<Item> findAll() {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }

    @Override
    public Optional<Item> findByTitle(String title) {
        return Optional.empty();
    }
}
