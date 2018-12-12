package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.AccDto;
import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.controller.dto.TransDto;
import ua.skillsup.examproject.isut.dao.entity.Account;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

import java.util.List;

public interface ActionService {

    /**
     * Retrieve all items from the system
     * @return all known items
     */
    Iterable<ItemDto> getAllItems();
    Iterable<OwnerDto> getActiveOwners();
    Iterable<OwnerDto> getAllOwners();
    Iterable<TransDto> getAllTransactions();
    Iterable<AccDto> getAllAccounts();
    Iterable<OwnerDto> getAllActiveOwners();

    long createItem(ItemDto itemDto, Long ownerid) throws NotEnoughDataToProcessTransaction;
    long createOwner(Owner owner);
    long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction;

    boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final Long id);
    boolean deleteOwner(final Long id);
}
