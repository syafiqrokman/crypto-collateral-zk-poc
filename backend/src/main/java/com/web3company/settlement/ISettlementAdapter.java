package com.web3company.settlement;

public interface ISettlementAdapter {
    String submitTransaction(String from, String to, BigDecimal amount);
    String getTransactionStatus(String txHash);
}
