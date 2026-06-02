package me.fit.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.fit.model.Account;
import me.fit.model.Transaction;
import me.fit.model.UploadedFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Dependent
public class TransactionService {

    @Inject
    private EntityManager em;

    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        Account account = em.find(Account.class, transaction.getAccount().getId());
        if (transaction.getType().equalsIgnoreCase("INCOME")) {
            account.setBalance(account.getBalance().add(transaction.getAmount()));
        } else if (transaction.getType().equalsIgnoreCase("EXPENSE")) {
            account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        }
        em.merge(account);
        return em.merge(transaction);
    }

    public List<Transaction> getTransactionsByCategory(String categoryName) {
        return em.createNamedQuery(Transaction.GET_TRANSACTIONS_BY_CATEGORY, Transaction.class)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    public List<Transaction> getTransactionsByType(String type) {
        return em.createNamedQuery(Transaction.GET_TRANSACTIONS_BY_TYPE, Transaction.class)
                .setParameter("type", type)
                .getResultList();
    }

    public List<Transaction> getTransactionsByAccountId(Long id) {
        return em.createNamedQuery(Transaction.GET_TRANSACTIONS_BY_ACCOUNT_ID, Transaction.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Transaction> getTransactionsByCategoryId(Long id) {
        return em.createNamedQuery(Transaction.GET_TRANSACTIONS_BY_CATEGORY_ID, Transaction.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Transaction getTransactionById(Long id) {
        return em.find(Transaction.class, id);
    }

    @Transactional
    public Transaction addUploadedFileToTransaction(Long transactionId, UploadedFile uploadedFile) {
        Transaction transaction = em.find(Transaction.class, transactionId);
        em.persist(uploadedFile);
        if (transaction.getUploadedFiles() == null) {
            transaction.setUploadedFiles(new ArrayList<>());
        }
        transaction.getUploadedFiles().add(uploadedFile);
        return transaction;
    }

    @Transactional
    public Transaction getTransactionWithFiles(Long id) {
        Transaction transaction = em.find(Transaction.class, id);
        if (transaction == null) {
            return null;
        }
        for (UploadedFile uploadedFile : transaction.getUploadedFiles()) {
            uploadedFile.setFile(new File(uploadedFile.getFilename()));
        }
        return transaction;
    }

}