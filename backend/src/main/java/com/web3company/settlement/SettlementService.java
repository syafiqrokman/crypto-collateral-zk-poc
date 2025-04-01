
package com.web3company.settlement;

import com.web3company.settlement.adapter.QuorumSettlementAdapter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class SettlementService {

    private final QuorumSettlementAdapter adapter;

    @Autowired
    public SettlementService(QuorumSettlementAdapter adapter) {
        this.adapter = adapter;
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
