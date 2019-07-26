package com.anz.ws.accounts.service;

import com.anz.ws.accounts.domain.Account;
import com.anz.ws.accounts.domain.Transaction;
import com.anz.ws.accounts.jsonbean.AccountResponse;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import com.anz.ws.accounts.repository.AccountRepository;
import com.anz.ws.accounts.repository.TransactionRepository;
import com.anz.ws.accounts.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @TestConfiguration
    static class AccountServiceImplTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountServiceImpl();
        }
    }

    @Test
    public void whenAccountsFoundForUserId() {
        String userId = "user1";
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(accountRepository.findAllByUserId(userId, pageable)).thenReturn(getAccountList());
        List<AccountResponse> accountList = accountService.getAccounts(userId, pageable);

        assertThat(accountList.size()).isGreaterThan(0);
    }

    @Test
    public void whenAccountsNotFoundForUserId() {
        String userId = "user1";
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(accountRepository.findAllByUserId(userId, pageable)).thenReturn(new ArrayList<Account>());
        List<AccountResponse> accountList = accountService.getAccounts(userId, pageable);

        assertThat(accountList.size()).isEqualTo(0);
    }

    @Test
    public void whenTxnFoundForAccountNo() {
        String accountNumber = "1100001";
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(transactionRepository.findByAccountNumber(accountNumber, pageable)).thenReturn(getTxnList());
        List<TransactionResponse> txnList = accountService.getTransactions(accountNumber, pageable);

        assertThat(txnList.size()).isGreaterThan(0);
    }

    @Test
    public void whenTxnNotFoundForAccountNo() {
        String accountNumber = "1100001";
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(transactionRepository.findByAccountNumber(accountNumber, pageable)).thenReturn(new ArrayList<Transaction>());
        List<TransactionResponse> txnList = accountService.getTransactions(accountNumber, pageable);

        assertThat(txnList.size()).isEqualTo(0);
    }

    private List<Account> getAccountList() {
        List<Account> accountList = new ArrayList<Account>();
        Account account = Account.builder().id(1L).accountNumber("110001").accountName("Account AU").build();
        accountList.add(account);
        return accountList;
    }

    private List<Transaction> getTxnList() {
        List<Transaction> txnList = new ArrayList<Transaction>();
        Account account = Account.builder().id(1L).accountNumber("110001").accountName("Account AU").build();
        Transaction txn = Transaction.builder().account(account).build();
        txnList.add(txn);
        return txnList;
    }

}
