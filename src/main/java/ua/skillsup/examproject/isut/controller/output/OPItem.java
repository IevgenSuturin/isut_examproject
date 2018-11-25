package ua.skillsup.examproject.isut.controller.output;

import ua.skillsup.examproject.isut.dao.entity.Item;

import javax.persistence.Entity;

@Entity
public class OPItem {
    public OPItem(Item item){
        id = item.getId();
        name = item.getTitle();
        desc = item.getDescription();
    }

    private long id;
    private String name;
    private String desc;
}
