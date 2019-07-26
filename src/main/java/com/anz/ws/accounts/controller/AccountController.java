package com.anz.ws.accounts.controller;

import com.anz.ws.accounts.constant.AccountServiceConstants;
import com.anz.ws.accounts.jsonbean.AccountResponse;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import com.anz.ws.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/account")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<AccountResponse>> getAccounts(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                                             @RequestParam(value = "size", defaultValue = "10") @Max(value = 100) @Positive int size,
                                                             @PathVariable String userId) {
        log.info("Request Received for getAccounts() method with userId:: " + userId + " with page :: " + page + " and page size :: " + size);
        List<AccountResponse> accounts = accountService.getAccounts(userId, PageRequest.of(page, size));
        if (accounts == null || accounts.size() == 0) {
            log.info(AccountServiceConstants.ACCOUNTS_NOT_FOUND_ERR + userId);
        } else {
            log.info("Account list for user::"+ userId + "is " +accounts);
        }

        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping(value = "/{accountNumber}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@RequestParam(value = "page", defaultValue = "0") @PositiveOrZero int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") @Max(value = 100) @Positive int size,
                                                                     @PathVariable @NotNull String accountNumber) {
        log.info("Request received for getTransactions() method account number:: " + accountNumber + " with page :: " + page + " and page size :: " + size);
        List<TransactionResponse> transactions = accountService.getTransactions(accountNumber, PageRequest.of(page, size));
        if (transactions == null || transactions.size() == 0) {
            log.info(AccountServiceConstants.TRANSACTIONS_NOT_FOUND_ERR + accountNumber);
        } else {
            log.info("Transaction list sent for account::"+ accountNumber + "is " +transactions);
        }
        return ResponseEntity.ok().body(transactions);
    }
}
