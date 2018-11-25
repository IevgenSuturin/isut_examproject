package ua.skillsup.examproject.isut.controller.dto;


import ua.skillsup.examproject.isut.dao.entity.Transaction;

import java.time.LocalDateTime;

public class TransDto {
    public TransDto(Transaction transaction) {
        this.id = transaction.getId();
        this.itemDto = new ItemDto(transaction.getItem());
        this.ownerDto = new OwnerDto(transaction.getOwner());
        this.count = transaction.getCount();
        this.localDate = transaction.getDate_stor();
    }

    private long id;
    private ItemDto itemDto;
    private OwnerDto ownerDto;
    private int count;
    private LocalDateTime localDate;

    public long getId() {
        return id;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public int getCount() {
        return count;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }
}