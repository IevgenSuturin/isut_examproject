package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Owner;
import ua.skillsup.examproject.isut.dao.entity.Transaction;

public interface TransRepository extends CrudRepository<Transaction, Long> {
    void deleteOwnerTransactions(Owner owner);
}
