package com.web3company.settlement.adapter;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SettlementIntegrationTest {

    private static QuorumSettlementAdapter adapter;

    // Local Quorum testnet with pre-funded dev account
    private static final String NODE_URL = "http://localhost:8545";
    private static final String PRIVATE_KEY = "0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63";
    private static final String RECEIVER = "0x0000000000000000000000000000000000001337";

    @BeforeAll
    static void setup() {
        adapter = new QuorumSettlementAdapter(NODE_URL, PRIVATE_KEY);
    }

    @Test
    @Order(1)
    void checkBalance_isNotZero() throws Exception {
        BigDecimal balance = adapter.getBalance(adapter.getAddress());
        System.out.println("🔎 Current Balance: " + balance + " ETH");
        assertTrue(balance.compareTo(BigDecimal.ZERO) > 0, "Balance should be > 0");
    }

    @Test
    @Order(2)
    void sendTransaction_success() throws Exception {
        String sender = adapter.getAddress();

        BigDecimal senderBefore = adapter.getBalance(sender);
        BigDecimal receiverBefore = adapter.getBalance(RECEIVER);

        adapter.sendTestTransaction(RECEIVER, BigDecimal.valueOf(0.001));

        BigDecimal senderAfter = adapter.getBalance(sender);
        BigDecimal receiverAfter = adapter.getBalance(RECEIVER);

        System.out.println("Sender before: " + senderBefore + " | after: " + senderAfter);
        System.out.println("Receiver before: " + receiverBefore + " | after: " + receiverAfter);

        assertTrue(senderBefore.compareTo(senderAfter) > 0, "Sender's balance should decrease.");
        assertTrue(receiverAfter.compareTo(receiverBefore) > 0, "Receiver's balance should increase.");
    }

    @Test
    @Order(3)
    void recordSettlement_success() throws Exception {
        String zkHash = "0xabc123def4567890";

        adapter.recordSettlement(zkHash);

        System.out.println("✅ zkHash settlement recorded on-chain");
    }


}
