package com.anz.ws.accounts.service;

import com.anz.ws.accounts.jsonbean.AccountResponse;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    /** Method to get the accounts by userId
     * @param userId
     * @param page
     * @return
     */
    List<AccountResponse> getAccounts(String userId, Pageable page);

    /** Method to get transactions by account number
     * @param accountNumber
     * @param page
     * @return
     */
    List<TransactionResponse> getTransactions(String accountNumber, Pageable page);
}
