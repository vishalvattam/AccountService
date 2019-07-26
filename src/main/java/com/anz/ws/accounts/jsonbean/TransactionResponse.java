package com.anz.ws.accounts.jsonbean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ApiModel(description = "Transaction Enquiry Response")
public class TransactionResponse {
    @ApiModelProperty(value = "Account number", example = "1100001")
    private String accountNumber;
    @ApiModelProperty(value = "Account Name", example = "Account AU")
    private String accountName;
    @ApiModelProperty(value = "Transaction value Date", example = "2019-05-30")
    private Date valueDate;
    @ApiModelProperty(value = "Account Currency", example = "AUD")
    private String currency;
    @ApiModelProperty(value = "Debit Amount", example = "100.5")
    private BigDecimal debitAmount;
    @ApiModelProperty(value = "Credit Amount", example = "1000.5")
    private BigDecimal creditAmount;
    @ApiModelProperty(value = "Transaction Debit or credit ", example = "Credit")
    private String debitCredit;
    @ApiModelProperty(value = "Transaction Number", example = "1000001")
    private String transactionNumber;
    @ApiModelProperty(value = "Transaction Narrative", example = "TTT 001")
    private String transactionNarrative;
}
