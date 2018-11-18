package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

import java.util.List;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    Iterable<Item> getAllItems();

    Iterable<Owner> getAllOwners();
}
