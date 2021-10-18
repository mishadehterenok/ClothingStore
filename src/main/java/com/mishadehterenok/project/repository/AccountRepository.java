package com.mishadehterenok.project.repository;

import com.mishadehterenok.project.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByLogin(String login);
}
