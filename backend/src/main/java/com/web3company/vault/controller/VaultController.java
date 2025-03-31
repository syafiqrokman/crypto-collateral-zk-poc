package com.web3company.vault.controller;

import com.web3company.vault.model.WithdrawalRequest;
import com.web3company.vault.service.VaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collateral")
public class VaultController {

    private final VaultService service;

    @Autowired
    public VaultController(VaultService service) {
        this.service = service;
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam String clientId, @RequestParam double amount) {
        System.out.println("Received deposit from " + clientId + " amount: " + amount);
        service.processDeposit(clientId, amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody WithdrawalRequest request) {
        service.initiateWithdrawal(request);
    }

    @PostMapping("/webhook")
    public void webhook(@RequestBody WithdrawalRequest request) {
        service.processWebhook(request);
    }
}
