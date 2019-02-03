package ua.skillsup.examproject.isut.service;

import ua.skillsup.examproject.isut.controller.dto.*;
import ua.skillsup.examproject.isut.dao.support.PeriodType;

public interface GetInformationService {
    Iterable<ItemDto> getAllItems();
    Iterable<OwnerDto> getActiveOwners();
    Iterable<OwnerDto> getAllOwners();
    Iterable<TransDto> getAllTransactions();
    Iterable<AccDto> getAllAccounts();

    Iterable<TransInfoDto> getStatisticForPeriod(PeriodType periodKind);
    Iterable<OwnerWithTotalPriceDto> getMostActiveOwners();
}
