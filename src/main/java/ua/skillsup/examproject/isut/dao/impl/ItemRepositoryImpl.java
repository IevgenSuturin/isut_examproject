package ua.skillsup.examproject.isut.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.examproject.isut.dao.ItemRepository;
import ua.skillsup.examproject.isut.dao.entity.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Logger logger = LoggerFactory.getLogger(ItemRepositoryImpl.class);

    private final EntityManager entityManager;
    @Autowired
    public ItemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long create(Item item) {
        return 0;
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public Item getOne(long id) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return entityManager.createQuery("from Item", Item.class).getResultList();
    }

    @Override
    public Optional<Item> findByTitle(String title) {
        return Optional.empty();
    }
}
