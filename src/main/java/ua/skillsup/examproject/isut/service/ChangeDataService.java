package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.ItemDto;
import ua.skillsup.examproject.isut.controller.dto.OwnerDto;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.exceptions.NotEnoughDataToProcessTransaction;

import java.util.List;

public interface ChangeDataService {
    Long createItem(ItemDto itemDto, Long ownerid) throws NotEnoughDataToProcessTransaction;
    Long createOwner(Owner owner);
    Long createTransaction(Long itemId, Long ownerId, Long count) throws NotEnoughDataToProcessTransaction;

    Boolean withdrawItems(List<ItemDto> itemDtoList, Long ownerId) throws NotEnoughDataToProcessTransaction;

    Boolean deleteItem(final Long id);
    Boolean deleteOwner(final Long id);
}
