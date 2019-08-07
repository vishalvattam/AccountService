package com.anz.ws.accounts.controller;

import com.anz.ws.accounts.jsonbean.AccountResponse;
import com.anz.ws.accounts.jsonbean.TransactionResponse;
import com.anz.ws.accounts.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenUserId_whenGetAccount_thenReturnJsonArray() throws Exception {
        Mockito.when(accountService.getAccounts("user1", PageRequest.of(0, 10))).thenReturn(getAccountResponseList());
        mockMvc.perform(get("/v1/account/{userId}", "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountNumber", is("110001")));
    }

    @Test
    public void givenUserId_whenGetAccount_withInvalidPageNo_thenReturn404() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{userId}", "user1")
                .param("page", "-1")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUserId_whenGetAccount_withInvalidPageSize_thenReturn404() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{userId}", "user1")
                .param("page", "0")
                .param("size", "-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAccountNo_whenGetTransactions_thenReturnJsonArray() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{accountNumber}/transactions", "110001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].transactionNumber", is("1100100")));
    }

    @Test
    public void givenAccountNo_withInvalidRequestParamType_thenReturn404() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{accountNumber}/transactions", "110001")
                .param("page", "notNumber")
                .param("size", "notNumber")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAccountNo_whenGetTransactions_WithInInvalidPageNo_thenReturn404() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{accountNumber}/transactions", "110001")
                .param("page", "-1")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAccountNo_whenGetTransactions_WithInInvalidPageSize_thenReturn404() throws Exception {
        Mockito.when(accountService.getTransactions("110001", PageRequest.of(0, 10))).thenReturn(getTxnResponseList());
        mockMvc.perform(get("/v1/account/{accountNumber}/transactions", "110001")
                .param("page", "0")
                .param("size", "-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private List<AccountResponse> getAccountResponseList() {
        List accountList = new ArrayList<AccountResponse>();
        AccountResponse accountResponse = AccountResponse.builder().accountNumber("110001").accountName("Account AU").accountType("Savings")
                .balanceDate(new Date()).currency("AUD").openingAvailableBalance(new BigDecimal("1000.45")).build();
        accountList.add(accountResponse);
        return accountList;
    }

    private List<TransactionResponse> getTxnResponseList() {
        List txnList = new ArrayList<TransactionResponse>();
        TransactionResponse transactionResponse = TransactionResponse.builder().transactionNumber("1100100").accountNumber("110001").accountName("Account AU").debitCredit("Debit")
                .creditAmount(new BigDecimal("1000.45")).currency("AUD").debitAmount(new BigDecimal("1000.45")).build();
        txnList.add(transactionResponse);
        return txnList;
    }
}
