package com.web3company.settlement;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuorumSettlementAdapterTest {

    private final QuorumSettlementAdapter adapter = new QuorumSettlementAdapter(
            "http://localhost:8545",
            "0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63"
    );

    @Test
    public void testCheckBalance() throws Exception {
        adapter.getBalance(adapter.getAddress());
    }

    @Test
    public void testSendTransaction() throws Exception {
        String toAddress = "0x627306090abaB3A6e1400e9345bC60c78a8BEf57";
        adapter.sendTestTransaction(toAddress, new BigDecimal("0.01"));
    }

    @Test
    public void testRecordSettlement() {
        // Arrange
        String testHash = "zkHashHere";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Act
        adapter.recordSettlement(testHash);

        // Assert
        System.setOut(originalOut);
        String expected = "📦 Settlement recorded with zkHash: " + testHash;
        assertTrue(outContent.toString().contains(expected));
    }

}
