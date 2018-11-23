package ua.skillsup.examproject.isut.dao.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TRANS_TYPES")
public class TransTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TrTypeIdGenerator")
    @SequenceGenerator(name = "TrTypeIdGenerator", sequenceName = "trans_type_sequence")
    @Column(name = "ID")
    private long id;

    protected TransTypes() {}

    public TransTypes(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESC")
    private String desc;

    @OneToMany(mappedBy = "trtype", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
        transaction.setTrtype(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransTypes that = (TransTypes) o;
        return Objects.equals(id, that.getId());
    }


    @Override
    public String toString() {
        return "TransTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public int hashCode() { return Objects.hash(id);  }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
