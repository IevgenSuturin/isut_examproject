package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.TransTypes;

import java.util.List;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    Iterable<Item> getAllItems();
    Iterable<Owner> getAllOwners();
    Iterable<TransTypes> getAllTrTypes();

    long createTrType(TransTypes transTypes);

    boolean deleteItem(final long id);
    long createItem(Item item);

    boolean addTransaction(long itemId, long ownerId, int count);
}
