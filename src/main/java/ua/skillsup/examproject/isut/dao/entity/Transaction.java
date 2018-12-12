package ua.skillsup.examproject.isut.dao.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    protected Transaction(){}

    public Transaction(Item item, Owner owner, long count) {
        this.item = item;
        this.owner = owner;
        this.count = count;
        this.date_stor = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TransIdGenerator")
    @SequenceGenerator(name = "TransIdGenerator", sequenceName = "trans_sequence")
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "count")
    private long count;

    @Column(name = "data_trans")
    private LocalDateTime date_stor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", item=" + item +
                ", count=" + count +
                ", owner=" + owner +
                '}';
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDateTime getDate_stor() { return date_stor;  }

    public void setDate_stor(LocalDateTime date_stor) { this.date_stor = date_stor; }
}

