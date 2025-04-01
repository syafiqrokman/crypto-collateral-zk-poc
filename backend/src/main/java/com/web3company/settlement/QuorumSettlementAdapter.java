package com.web3company.settlement;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.net.DatagramPacket;

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

    // 1️⃣ Check balance of current signer
    public BigDecimal getBalance(String address) throws Exception {
        EthGetBalance balance = web3.ethGetBalance(
                address, DefaultBlockParameterName.LATEST
        ).send();

        return Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);
    }


    // 2️⃣ Send ETH to another address
    public void sendTestTransaction(String toAddress, BigDecimal amountEth) throws Exception {
        TransactionReceipt receipt = Transfer.sendFunds(
                web3, credentials, toAddress, amountEth, Convert.Unit.ETHER
        ).send();

        System.out.println("✅ Tx sent. Hash: " + receipt.getTransactionHash());
    }

    public void recordSettlement(String zkHash) throws Exception {
        SettlementLogger contract = SettlementLogger.load(
                CONTRACT_ADDRESS, web3, credentials, new DefaultGasProvider()
        );

        TransactionReceipt receipt = contract.recordSettlement(zkHash).send();
        System.out.println("📦 zkHash recorded. Tx hash: " + receipt.getTransactionHash());
    }


    public String getAddress() {
        return credentials.getAddress();
    }

}
