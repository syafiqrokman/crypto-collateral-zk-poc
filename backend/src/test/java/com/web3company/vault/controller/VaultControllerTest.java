package com.web3company.vault.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web3company.vault.model.WithdrawalRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.web3company.vault.service.VaultService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VaultController.class)
public class VaultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaultService vaultService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDepositEndpoint() throws Exception {
        mockMvc.perform(post("/api/collateral/deposit")
                        .param("clientId", "client1")
                        .param("amount", "1000"))
                .andExpect(status().isOk());
    }

    @Test
    void testWithdrawEndpoint() throws Exception {
        WithdrawalRequest request = new WithdrawalRequest();
        request.setClientId("client1");
        request.setAmount(1000);
        request.setWithdrawalId("w123");

        mockMvc.perform(post("/api/collateral/withdraw")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
