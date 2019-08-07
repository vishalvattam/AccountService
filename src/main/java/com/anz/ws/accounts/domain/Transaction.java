package com.anz.ws.accounts.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    private String currency;
    private Date valueDate;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String debitCredit;
    private String transactionNumber;
    private String transactionNarrative;
}
