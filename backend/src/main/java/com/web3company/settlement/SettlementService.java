
package com.web3company.settlement;

import com.web3company.settlement.adapter.QuorumSettlementAdapter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SettlementService {

    private final QuorumSettlementAdapter adapter;

    public SettlementService() {
        this.adapter = new QuorumSettlementAdapter(
                "http://localhost:8545",
                "0x8f2a55949038a9610f50fb23b5883af3b4ecb3c3bb792cbcefbd1542c692be63"
        );
    }

    public BigDecimal getBalance(String address) throws Exception {
        return adapter.getBalance(address);
    }

    public void sendEth(String toAddress, BigDecimal amount) throws Exception {
        adapter.sendTestTransaction(toAddress, amount);
    }

    public void recordZkSettlement(String zkHash) throws Exception {
        adapter.recordSettlement(zkHash);
    }

    public String generateZkHash() {
        return "zkHash-" + UUID.randomUUID();
    }
}
