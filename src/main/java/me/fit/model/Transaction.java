package me.fit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = Transaction.GET_TRANSACTIONS_BY_CATEGORY,
        query = "Select t from Transaction t where t.category.name = :categoryName")
@NamedQuery(name = Transaction.GET_TRANSACTIONS_BY_TYPE,
        query = "Select t from Transaction t where t.type = :type")
@NamedQuery(name = Transaction.GET_TRANSACTIONS_BY_ACCOUNT_ID,
        query = "Select t from Transaction t where t.account.id = :id")
@NamedQuery(name = Transaction.GET_TRANSACTIONS_BY_CATEGORY_ID,
        query = "Select t from Transaction t where t.category.id = :id")
public class Transaction {

    public static final String GET_TRANSACTIONS_BY_CATEGORY = "GetTransactionsByCategory";
    public static final String GET_TRANSACTIONS_BY_TYPE = "GetTransactionsByType";
    public static final String GET_TRANSACTIONS_BY_ACCOUNT_ID = "GetTransactionsByAccountId";
    public static final String GET_TRANSACTIONS_BY_CATEGORY_ID = "GetTransactionsByCategoryId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", allocationSize = 1)
    private Long id;

    private BigDecimal amount;
    private LocalDate date;
    private String type;

    @Transient // za sad je ovako jer jos nismo radili @ManyToMany
    private List<Tag> tags;

    @JsonBackReference("account-transactions")
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonBackReference("category-transactions")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", tags=" + tags +
                ", account=" + account +
                ", category=" + category +
                '}';
    }
}
