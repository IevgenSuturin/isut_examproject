package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

import java.util.List;

public interface ChangeDataService {
    long createItem(ItemDto itemDto, Long ownerid) throws NotEnoughDataToProcessTransaction;
    long createOwner(Owner owner);
    long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction;

    boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction;

    boolean deleteItem(final Long id);
    boolean deleteOwner(final Long id);
}
