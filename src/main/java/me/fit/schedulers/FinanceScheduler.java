package me.fit.schedulers;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import me.fit.model.Account;

import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class FinanceScheduler {

    @Inject
    EntityManager em;

    @Scheduled(every = "60s")
    public void checkNegativeBalance() {
        List<Account> accounts = em.createQuery("Select a from Account a", Account.class).getResultList();
        for (Account account : accounts) {
            if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("UPOZORENJE: Racun '" + account.getName() + "' ima negativan balans: " + account.getBalance());
            }
        }
    }
}
