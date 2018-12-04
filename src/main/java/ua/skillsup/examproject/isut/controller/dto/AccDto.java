package ua.skillsup.examproject.isut.controller.dto;

import ua.skillsup.examproject.isut.dao.entity.Account;

public class AccDto {
    public AccDto(){}

    public AccDto(Account account){
        this.id = account.getId();
        this.item_id = account.getItem().getId();
        this.owner_id = account.getOwner().getId();
        this.count = account.getCount();
    }

    private long id;
    private long item_id;
    private long owner_id;
    private long count;

    public long getId() {
        return id;
    }

    public long getItem_id() {
        return item_id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public long getCount() {
        return count;
    }
}
