package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.*;
import ua.skillsup.examproject.isut.dao.support.PeriodType;

import java.util.Optional;

public interface GetInformationService {
    Iterable<ItemDto> getAllItems();
    Iterable<OwnerDto> getActiveOwners();
    Iterable<OwnerDto> getAllOwners();
    Iterable<TransDto> getAllTransactions();
    Iterable<AccDto> getAllAccounts();

    Optional<ItemDto> findItemByTitle(String title);
    Optional<OwnerDto> findOwnerByByFirstName(String firstName);

    Iterable<TransInfoDto> getStatisticForPeriod(PeriodType periodKind);
    Iterable<OwnerWithTotalPriceDto> getMostActiveOwners();
}
