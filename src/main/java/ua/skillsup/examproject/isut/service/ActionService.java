package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.TransTypes;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

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
    long createItem(Item item);
    long createOwner(Owner owner);
    long createTransaction(Transaction transaction) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final long id);
    boolean deleteOwner(final long id);

    boolean addTransaction(long itemId, long ownerId, int count);
}
