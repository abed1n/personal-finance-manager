package me.fit.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_seq")
    @SequenceGenerator(name = "budget_seq", sequenceName = "budget_seq", allocationSize = 1)
    private Long id;

    private String name;
    private BigDecimal limitAmount;

    @Transient //za sad je ovako jer jos nismo radili @ManyToMany
    private List<Category> categories;

    public Budget() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Budget budget)) return false;
        return Objects.equals(id, budget.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limitAmount=" + limitAmount +
                ", categories=" + categories +
                '}';
    }
}
