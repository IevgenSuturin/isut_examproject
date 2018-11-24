package ua.skillsup.examproject.isut.dao.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT")
public class Account {
    protected Account(){}

    public Account(Item item, Owner owner, int count) {
        this.item = item;
        this.owner = owner;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountIdGenerator")
    @SequenceGenerator(name = "AccountIdGenerator", sequenceName = "account_sequence")
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "count")
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", item=" + item +
                ", owner=" + owner +
                ", count=" + count +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
