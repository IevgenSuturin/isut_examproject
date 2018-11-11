package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.dao.entity.Item;

import java.util.List;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    List<Item> getAllItems();

}
