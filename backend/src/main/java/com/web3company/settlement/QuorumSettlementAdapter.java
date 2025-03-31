package com.web3company.settlement;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

public class QuorumSettlementAdapter implements ISettlementAdapter {

    private final Web3j web3;
    private final Credentials credentials;

    public QuorumSettlementAdapter(String nodeUrl, String privateKey) {
        this.web3 = Web3j.build(new HttpService(nodeUrl));
        this.credentials = Credentials.create(privateKey);
    }

    @Override
    public String submitTransaction(String from, String to, BigDecimal amount) {
        try {
            RawTransactionManager txManager = new RawTransactionManager(web3, credentials);
            // Simulate sending ETH; may be used to send other txns or logic or smart contract calls later
            var txHash = txManager.sendTransaction(
                    DefaultGasProvider.GAS_PRICE,
                    DefaultGasProvider.GAS_LIMIT,
                    to,
                    "", // no data
                    amount.toBigInteger()
            ).getTransactionHash();

            return txHash;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }

    @Override
    public String getTransactionStatus(String txHash) {
        try {
            var receipt = web3.ethGetTransactionReceipt(txHash).send();
            return receipt.getTransactionReceipt().isPresent() ? "CONFIRMED" : "PENDING";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
