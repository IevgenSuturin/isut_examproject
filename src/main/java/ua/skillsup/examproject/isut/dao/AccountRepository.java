package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByOwnerAndItem(long ownerId, long itemId);
}
