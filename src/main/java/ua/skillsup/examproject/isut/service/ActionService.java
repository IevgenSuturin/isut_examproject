package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.controller.dto.TransDto;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    Iterable<ItemDto> getAllItems();
    Iterable<OwnerDto> getAllOwners();
    Iterable<TransDto> getAllTransactions();

    long createItem(ItemDto itemDto, long ownerid) throws NotEnoughDataToProcessTransaction;
    long createOwner(Owner owner);
    long createTransaction(Transaction transaction) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final long id);
    boolean deleteOwner(final long id);
}
