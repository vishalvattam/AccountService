package com.anz.ws.accounts.IT;

public class TestData {

    public static String getExpectedDataForUser1() {

        return "[{\"id\":1,\"userId\":\"user1\",\"accountNumber\":\"100001\",\"accountName\":\"Account AU\",\"accountType\":\"Saving\",\"balanceDate\":\"2019-05-20T18:30:00.000+0000\",\"currency\":\"AUD\",\"openingAvailableBalance\":300.0},{\"id\":2,\"userId\":\"user1\",\"accountNumber\":\"100002\",\"accountName\":\"Account US\",\"accountType\":\"Current\",\"balanceDate\":\"2019-05-21T18:30:00.000+0000\",\"currency\":\"USD\",\"openingAvailableBalance\":400.0},{\"id\":3,\"userId\":\"user1\",\"accountNumber\":\"100003\",\"accountName\":\"Account IN\",\"accountType\":\"Saving\",\"balanceDate\":\"2019-05-22T18:30:00.000+0000\",\"currency\":\"IND\",\"openingAvailableBalance\":500.0}]";
    }

    public static String getExpectedDataForAccount1() {
        return "[{\"id\":11,\"accountId\":1,\"accountNumber\":\"100001\",\"accountName\":\"Account AU\",\"valueDate\":\"2019-05-21T18:30:00.000+0000\",\"currency\":\"AUD\",\"debitAmount\":450.0,\"creditAmount\":null,\"debitCredit\":\"Debit\",\"transactionNumber\":\"100010011\"},{\"id\":12,\"accountId\":1,\"accountNumber\":\"100001\",\"accountName\":\"Account AU\",\"valueDate\":\"2019-05-22T18:30:00.000+0000\",\"currency\":\"AUD\",\"debitAmount\":null,\"creditAmount\":100.0,\"debitCredit\":\"Credit\",\"transactionNumber\":\"100010012\"},{\"id\":13,\"accountId\":1,\"accountNumber\":\"100001\",\"accountName\":\"Account AU\",\"valueDate\":\"2019-05-23T18:30:00.000+0000\",\"currency\":\"AUD\",\"debitAmount\":null,\"creditAmount\":50.0,\"debitCredit\":\"Credit\",\"transactionNumber\":\"100010013\",\"transactionNarrative\":\"TTT 111\"}]";
    }
}
