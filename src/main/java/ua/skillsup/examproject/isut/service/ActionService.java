package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.*;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.support.PeriodType;
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

    long createItem(ItemDto itemDto, Long ownerid) throws NotEnoughDataToProcessTransaction;
    long createOwner(Owner owner);
    long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction;

    boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final Long id);
    boolean deleteOwner(final Long id);

    Iterable<TransInfoDto> getStatisticForPeriod(PeriodType periodKind);
    Iterable<OwnerWithTotalPriceDto> getMostActiveOwners();
}
