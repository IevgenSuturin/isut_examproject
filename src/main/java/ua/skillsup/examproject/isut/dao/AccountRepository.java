package ua.skillsup.examproject.isut.dao;

import ua.skillsup.examproject.isut.dao.entity.Account;
import ua.skillsup.examproject.isut.dao.entity.Item;
import ua.skillsup.examproject.isut.dao.entity.Owner;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByOwnerAndItem(Owner owner, Item item);
    boolean isActiveOwnerAccountExists(Owner owner);
    void deleteOwnerAccounts(Owner owner);
}
