package me.fit.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.model.Account;
import me.fit.model.User;

import java.util.List;

@Dependent
public class UserService {

    @Inject
    private EntityManager em;

    @Transactional
    public User createUser(User user) {
        return em.merge(user);
    }

    public List<User> getAllUsers() {
        return em.createNamedQuery(User.GET_ALL_USERS, User.class).getResultList();
    }

    public List<Account> getAccountsByUserId(Long id) {
        return em.createNamedQuery(Account.GET_ACCOUNTS_BY_USER_ID, Account.class)
                .setParameter("id", id)
                .getResultList();
    }

}
