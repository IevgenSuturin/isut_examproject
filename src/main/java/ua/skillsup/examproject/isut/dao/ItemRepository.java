package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.entity.Item;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Optional<ItemDto> findByTitle(String title);
}
