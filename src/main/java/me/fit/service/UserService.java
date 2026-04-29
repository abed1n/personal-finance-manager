package me.fit.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.exception.UserNotFoundException;
import me.fit.model.*;

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

    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public User addTimezoneToUser(Long userId, TimezoneResponse timezoneResponse) throws UserNotFoundException {
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        timezoneResponse.setUser(user);
        user.getTimezones().add(timezoneResponse);
        return em.merge(user);
    }

    @Transactional
    public User addLocationToUser(Long userId, LocationResponse locationResponse) throws UserNotFoundException {
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        locationResponse.setUser(user);
        user.getLocations().add(locationResponse);
        return em.merge(user);
    }

    @Transactional
    public User addCurrencyConversionToUser(Long userId, CurrencyResponse currencyResponse) throws UserNotFoundException {
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        currencyResponse.setUser(user);
        user.getCurrencies().add(currencyResponse);
        return em.merge(user);
    }
}
