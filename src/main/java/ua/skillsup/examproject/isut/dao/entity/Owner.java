package ua.skillsup.examproject.isut.dao.entity;

import ua.skillsup.examproject.isut.controller.dto.OwnerDto;

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

    public Owner(OwnerDto ownerDto){
        this.company_name = ownerDto.getCmpname();
        this.first_name = ownerDto.getFname();
        this.last_name = ownerDto.getLname();
        this.id = ownerDto.getId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OwnerGenerator")
    @SequenceGenerator(name = "OwnerGenerator", sequenceName = "owner_sequence")
    @Column(name = "ID")
    private Long id;

    @Column(name = "FNAME")
    private String first_name;

    @Column(name = "LNAME")
    private String last_name;

    @Column(name = "CMPNAME")
    private String company_name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
