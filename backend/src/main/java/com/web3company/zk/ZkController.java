package com.web3company.zk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/zk")
public class ZkController {

    @Autowired
    private ZkService zkService;

    @PostMapping("/settle")
    public String simulateZkSettlement(
            @RequestParam String to,
            @RequestParam BigDecimal amount
    ) throws Exception {
        String zkHash = zkService.generateProof(to, amount);
        boolean verified = zkService.verifyProof(zkHash);

        if (verified) {
            zkService.postSettlement(zkHash);
            return "✅ ZK settlement successful with zkHash: " + zkHash;
        } else {
            return "❌ ZK proof verification failed";
        }
    }
}
