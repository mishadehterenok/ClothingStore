package com.mishadehterenok.project.service;

import com.mishadehterenok.project.entity.Account;
import com.mishadehterenok.project.entity.Order;
import com.mishadehterenok.project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService implements BaseService<Account> {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Account account){
        accountRepository.save(account);
    }

    public Account findByLogin(String login){
        return accountRepository.findByLogin(login);
    }

    @Override
    public Account findById(Long id){
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public void updateAccountAfterPurchaseOrders(Long accountId, Double totalAmount, List<Order> orders){
        Account account = findById(accountId);
        account.subtractAmount(totalAmount);
        orders.forEach(order -> order.getClothing().subtractQuantity(order.getQuantity()));
        account.removeAllOrders(orders);
        save(account);
    }

    public void updateAccountAfterPurchaseOneOrder(Long accountId, Order order){
        Account account = findById(accountId);
        account.subtractAmount(order.getFinalPrice());
        order.getClothing().subtractQuantity(order.getQuantity());
        account.removeOrder(order);
        save(account);
    }
}
