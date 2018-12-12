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

    private Long id;
    private Long item_id;
    private Long owner_id;
    private long count;

    public Long getId() {
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
