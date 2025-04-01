package com.web3company.settlement.adapter;

import java.math.BigDecimal;

public interface ISettlementAdapter {
    String submitTransaction(String from, String to, BigDecimal amount);
    String getTransactionStatus(String txHash);
}
