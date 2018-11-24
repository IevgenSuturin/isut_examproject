package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<Item> findByTitle(String title);
}
