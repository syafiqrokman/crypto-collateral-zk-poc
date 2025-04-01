package com.web3company.settlement;

import com.web3company.settlement.contracts.SettlementLogger;
import org.junit.jupiter.api.Test;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class QuorumSettlementAdapterTest {

    private final String prefundedPrivateKey = "0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63";
    private final String CONTRACT_ADDRESS = "0xF216B6b2D9E76F94f97bE597e2Cec81730520585";
    private final QuorumSettlementAdapter adapter = new QuorumSettlementAdapter(
            "http://localhost:8545",
            prefundedPrivateKey  // using prefunded address for testing purposes
    );

    @Test
    public void testCheckBalance() throws Exception {
        BigDecimal balance = adapter.getBalance(adapter.getAddress());
        System.out.println("✅ Prefunded account balance: " + balance + " ETH");
        assertTrue(balance.compareTo(BigDecimal.ZERO) > 0, "Expected non-zero balance");
    }

    @Test
    public void testSendTransaction() throws Exception {
        String toAddress = "0x627306090abaB3A6e1400e9345bC60c78a8BEf57";  // Known test address
        adapter.sendTestTransaction(toAddress, new BigDecimal("0.01"));
        System.out.println("✅ ETH sent successfully to " + toAddress);
    }

    @Test
    public void testRecordSettlement() throws Exception {
        String zkHash = "zkHashHere-" + UUID.randomUUID();
        TransactionReceipt receipt = adapter.recordSettlement(zkHash);

        List<SettlementLogger.SettlementRecordedEventResponse> events =
                adapter.getSettlementEvents(receipt);
        System.out.println(events.size());
        assertFalse(events.isEmpty(), "Expected at least one SettlementRecorded event");

        SettlementLogger.SettlementRecordedEventResponse event = events.get(0);
        assertEquals(zkHash, event.zkHash);
        System.out.println("✅ Verified emitted zkHash event: " + event.zkHash);
    }




}
