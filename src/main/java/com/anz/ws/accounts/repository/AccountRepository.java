package com.anz.ws.accounts.repository;

import com.anz.ws.accounts.domain.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserId(String userId, Pageable page);
}
