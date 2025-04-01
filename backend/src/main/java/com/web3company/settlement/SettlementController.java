
package com.web3company.settlement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/settlement")
@Validated
public class SettlementController {

    @Autowired
    private SettlementService service;

    @GetMapping("/balance")
    public BigDecimal checkBalance(@RequestParam @NotBlank String address) throws Exception {
        return service.getBalance(address);
    }

    @PostMapping("/send")
    public void sendEth(
            @RequestParam @NotBlank String to,
            @RequestParam @Positive BigDecimal amount
    ) throws Exception {
        service.sendEth(to, amount);
    }

    @PostMapping("/record")
    public void recordSettlement(
            @RequestParam @NotBlank String zkHash
    ) throws Exception {
        service.recordZkSettlement(zkHash);
    }
}
