package com.web3company.vault.service;

import com.web3company.vault.model.WithdrawalRequest;
import com.web3company.vault.repository.ClientBalanceRepository;
import com.web3company.vault.external.WalletClient;
import com.web3company.vault.logic.CTREngine;
import com.web3company.vault.model.ClientBalance;
import org.springframework.stereotype.Service;

@Service
public class VaultService {

    private final ClientBalanceRepository repository;
    private final WalletClient walletClient;
    private final CTREngine ctrEngine;

    public VaultService(ClientBalanceRepository repository,
                        WalletClient walletClient,
                        CTREngine ctrEngine) {
        this.repository = repository;
        this.walletClient = walletClient;
        this.ctrEngine = ctrEngine;
    }

    public void processDeposit(String clientId, double amount) {
        ClientBalance balance = repository.findById(clientId)
                .orElseGet(() -> new ClientBalance());

        balance.setClientId(clientId);
        balance.setCollateral(balance.getCollateral() + amount);

        repository.save(balance);
    }

    public void initiateWithdrawal(WithdrawalRequest request) {
        // TODO: Validate, deduct, call WalletClient
        System.out.println("Initiating withdrawal: " + request);
    }

    public void processWebhook(WithdrawalRequest request) {
        // TODO: Confirm idempotency, finalize status
        System.out.println("Processing webhook for: " + request);
    }
}
