package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.controller.dto.TransInfoDto;
import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;

import java.time.LocalDate;

public interface TransRepository extends CrudRepository<Transaction, Long> {
    void deleteOwnerTransactions(Owner owner);
    TransInfoDto getStatisticForPeriod(LocalDate dateStart, boolean stored);
}
