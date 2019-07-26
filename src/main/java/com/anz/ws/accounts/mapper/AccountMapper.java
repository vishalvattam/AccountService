package com.anz.ws.accounts.mapper;

import com.anz.ws.accounts.domain.Account;
import com.anz.ws.accounts.jsonbean.AccountResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountMapper {

    public static List<AccountResponse> toAccountResponse(List<Account> accounts) {

        return Optional.ofNullable(accounts).orElseGet(ArrayList::new)
            .stream()
            .map(AccountMapper::toAccountResponse)
            .collect(Collectors.toList());
    }

    public static AccountResponse toAccountResponse(Account account) {
        AccountResponse accountResponse = AccountResponse.builder().build();
        BeanUtils.copyProperties(account, accountResponse);
        return accountResponse;
    }
}
