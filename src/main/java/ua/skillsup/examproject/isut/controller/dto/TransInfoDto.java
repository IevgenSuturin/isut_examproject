package ua.skillsup.examproject.isut.controller.dto;

public class TransInfoDto {
    private long amount;
    private long count;
    private String description;

    public TransInfoDto(long amount, long count, String description) {
        this.amount = amount;
        this.count = count;
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
