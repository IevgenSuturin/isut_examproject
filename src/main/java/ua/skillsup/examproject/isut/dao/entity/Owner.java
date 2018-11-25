package ua.skillsup.examproject.isut.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "OWNER")
public class Owner {
    protected Owner(){}

    public Owner(String first_name, String last_name, String company_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.company_name = company_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OwnerGenerator")
    @SequenceGenerator(name = "OwnerGenerator", sequenceName = "owner_sequence")
    @Column(name = "ID")
    private long id;

    @Column(name = "FNAME")
    private String first_name;

    @Column(name = "LNAME")
    private String last_name;

    @Column(name = "CMPNAME")
    private String company_name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
        transaction.setOwner(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", company_name='" + company_name + '\'' +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
