package ua.skillsup.examproject.isut.controller.dto;

import ua.skillsup.examproject.isut.dao.entity.Owner;

public class OwnerWithTotalPriceDto {
    public OwnerWithTotalPriceDto(Owner owner, Long totalPrice) {
        this.id = owner.getId();
        this.fname = owner.getFirst_name();
        this.lname = owner.getLast_name();
        this.cmpname = owner.getCompany_name();
        this.totalPrice = totalPrice;
    }

    protected OwnerWithTotalPriceDto(){}

    private Long id;
    private String fname;
    private String lname;
    private String cmpname;
    private Long totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCmpname() {
        return cmpname;
    }

    public void setCmpname(String cmpname) {
        this.cmpname = cmpname;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
