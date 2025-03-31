package com.web3company.vault.external;

import com.web3company.vault.model.WithdrawalRequest;
import org.springframework.stereotype.Component;

@Component
public class WalletClient {

    public void sendWithdrawal(WithdrawalRequest request) {
        // TODO: Replace with real HTTP call to Wallet Service
        System.out.println("Calling WalletService for withdrawal: " + request);
    }
}
