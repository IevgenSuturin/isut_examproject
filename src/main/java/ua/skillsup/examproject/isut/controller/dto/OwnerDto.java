package ua.skillsup.examproject.isut.controller.dto;

import ua.skillsup.examproject.isut.dao.entity.Owner;

public class OwnerDto {
    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.fname = owner.getFirst_name();
        this.lname = owner.getLast_name();
        this.cmpname = owner.getCompany_name();
    }

    private long id;
    private String fname;
    private String lname;
    private String cmpname;

    public long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCmpname() {
        return cmpname;
    }
}
