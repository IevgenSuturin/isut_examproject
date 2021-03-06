package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    private final EntityManager entityManager;

    @Override
    public boolean delete(Item item) {
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
    public Long create(Item item) {
        if(item.getId() == null){
            entityManager.persist(item);
        }
        return  item.getId();
    }

    @Override
    public void update(Item item) {
        if(item.getId() != null){
            entityManager.merge(item);
        }
    }

    @Override
    public Item getOne(Long id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public Iterable<Item> findAll() {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }

    @Override
    public Optional<ItemDto> findByTitle(String title) {
        try{
        return Optional.of(new ItemDto(entityManager
                            .createQuery("from Item where title=:param", Item.class)
                            .setParameter("param", title)
                            .getSingleResult()));
        } catch (NoResultException ex){
            return Optional.empty();
        }
    }
}
