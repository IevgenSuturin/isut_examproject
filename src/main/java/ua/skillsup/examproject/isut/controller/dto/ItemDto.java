package ua.skillsup.examproject.isut.controller.dto;

import ua.skillsup.examproject.isut.dao.entity.Item;

public class ItemDto {
    public ItemDto(Item item){
        id = item.getId();
        title = item.getTitle();
        desc = item.getDescription();
    }

    private long id;
    private String title;
    private String desc;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
