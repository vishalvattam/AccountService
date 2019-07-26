package com.anz.ws.accounts.service.impl;

import com.anz.ws.accounts.jsonbean.AccountResponse;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import com.anz.ws.accounts.mapper.AccountMapper;
import com.anz.ws.accounts.mapper.TransactionMapper;
import com.anz.ws.accounts.repository.AccountRepository;
import com.anz.ws.accounts.repository.TransactionRepository;
import com.anz.ws.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Service method to return the list of accounts for given user id
     * @param userId
     * @param page
     * @return AccountsList
     */
    @Override
    public List<AccountResponse> getAccounts(String userId, Pageable page) {
        return AccountMapper.toAccountResponse(accountRepository.findAllByUserId(userId, page));
    }

    /**
     * Service method to return the list of transactions for given account
     * @param accountNumber
     * @param page
     * @return TransactionsList
     */
    @Override
    public List<TransactionResponse> getTransactions(String accountNumber, Pageable page) {
        return TransactionMapper.toTransactionResponse(transactionRepository.findByAccountNumber(accountNumber, page));
    }
}
