package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    long create(Item item);
    void update(Item item);
    Item getOne(long id);
    List<Item> findAll();
    Optional<Item> findByTitle(String title);
}
