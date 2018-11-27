package ua.skillsup.examproject.isut.controller.dto;

import ua.skillsup.examproject.isut.dao.entity.Item;

public class ItemDto {
    public ItemDto(Item item){
        id = item.getId();
        title = item.getTitle();
        desc = item.getDescription();
        count = item.getCount();
        price = item.getPrice();
    }
    protected ItemDto(){}

    private long id;
    private String title;
    private String desc;
    private long count;
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPriceDto() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
