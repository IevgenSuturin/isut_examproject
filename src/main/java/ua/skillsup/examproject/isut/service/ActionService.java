package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.output.OPItem;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    Iterable<OPItem> getAllItems();
    Iterable<Owner> getAllOwners();
    Iterable<Transaction> getAllTransactions();

    long createItem(Item item);
    long createOwner(Owner owner);
    long createTransaction(Transaction transaction) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final long id);
    boolean deleteOwner(final long id);
}
