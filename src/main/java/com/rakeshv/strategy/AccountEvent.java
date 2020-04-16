package com.rakeshv.strategy;

import java.lang.reflect.InvocationTargetException;

import com.rakeshv.models.Account;
import com.rakeshv.repositories.AccountRepository;

public class AccountEvent implements EventType {
    private final AccountRepository accountRepository;

    public AccountEvent(AccountRepository repository) {
        this.accountRepository = repository;

        if (accountRepository.findAll().size() == 0) {
            Account account = Account.builder().build();
            accountRepository.save(account);
        }
    }

    @Override
    public void processEvent(String[] action) {
        String function = action.length > 2 ? action[1].toLowerCase() + action[2].toLowerCase() :
                                                action[1].toLowerCase();

        try {
            this.getClass().getDeclaredMethod(function).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private Account getAccount() {
        return accountRepository.findAll().get(0);
    }

    private void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void create() {
        Account account = getAccount();
        account.setAccountCreate(account.getAccountCreate() + 1);
        saveAccount(account);
    }

    public void delete() {
        Account account = getAccount();
        account.setAccountDelete(account.getAccountDelete() + 1);
        saveAccount(account);
    }

    public void update() {
        Account account = getAccount();
        account.setAccountUpdate(account.getAccountUpdate() + 1);
        saveAccount(account);
    }

    public void enable() {
        Account account = getAccount();
        account.setAccountEnable(account.getAccountEnable() + 1);
        saveAccount(account);
    }

    public void disable() {
        Account account = getAccount();
        account.setAccountDisable(account.getAccountDisable() + 1);
        saveAccount(account);
    }
    
}