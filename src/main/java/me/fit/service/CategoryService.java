package me.fit.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.model.Category;

@Dependent
public class CategoryService {

    @Inject
    EntityManager em;

    @Transactional
    public Category addCategory(Category category) {
        return em.merge(category);
    }
}
