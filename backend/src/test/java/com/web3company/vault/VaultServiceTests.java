package com.web3company.vault;

import com.web3company.vault.model.ClientBalance;
import com.web3company.vault.repository.ClientBalanceRepository;
import com.web3company.vault.service.VaultService;
import com.web3company.vault.external.WalletClient;
import com.web3company.vault.logic.CTREngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class VaultServiceTests {

    @MockBean
    private ClientBalanceRepository repo;

    @MockBean
    private WalletClient walletClient;

    @MockBean
    private CTREngine ctrEngine;

    @Test
    void testDepositUpdatesBalance() {
        VaultService service = new VaultService(repo, walletClient, ctrEngine);

        ClientBalance balance = new ClientBalance();
        balance.setClientId("user1");
        balance.setCollateral(100);

        when(repo.findById("user1")).thenReturn(java.util.Optional.of(balance));

        service.processDeposit("user1", 50);

        verify(repo).save(argThat(b ->
                b.getClientId().equals("user1") &&
                        b.getCollateral() == 150
        ));
    }
}
