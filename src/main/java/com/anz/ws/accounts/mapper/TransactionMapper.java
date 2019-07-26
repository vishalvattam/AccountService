package com.anz.ws.accounts.mapper;

import com.anz.ws.accounts.domain.Transaction;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static List<TransactionResponse> toTransactionResponse(List<Transaction> transactions) {

        return Optional.ofNullable(transactions).orElseGet(ArrayList::new)
                .stream()
                .map(TransactionMapper::toTransactionResponse)
                .collect(Collectors.toList());
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        TransactionResponse transactionResponse = TransactionResponse.builder().build();
        BeanUtils.copyProperties(transaction, transactionResponse);
        transactionResponse.setAccountNumber(transaction.getAccount().getAccountNumber());
        transactionResponse.setAccountName(transaction.getAccount().getAccountName());
        return transactionResponse;
    }
}
