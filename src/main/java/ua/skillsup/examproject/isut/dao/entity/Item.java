package ua.skillsup.examproject.isut.dao.entity;

import ua.skillsup.examproject.isut.controller.dto.ItemDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ITEM")
public class Item {

    protected Item(){}

    public Item(String title, String description, long price) {
        this.title = title;
        this.description = description;
        this.count = 0;
        this.price = price;
        this.dateStore = LocalDateTime.now().plusYears(1);
    }

    public Item (ItemDto itemDto){
        this.title = itemDto.getTitle();
        this.description = itemDto.getDesc();
        this.price = itemDto.getPriceDto();
        this.count = itemDto.getCount();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ItemIdGenerator")
    @SequenceGenerator(name = "ItemIdGenerator", sequenceName = "item_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COUNT", nullable = false)
    private long count;
    @Column(name = "PRICE")
    private long price;
    @Column(name = "DATE_STOR")
    private LocalDateTime dateStore;


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
        transaction.setItem(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", dateStore=" + dateStore +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public LocalDateTime getDate_stor() {
        return dateStore;
    }

    public void setDate_stor(LocalDateTime dateStore) {
        this.dateStore = dateStore;
    }
}
