package com.web3company.vault.repository;

import com.web3company.vault.model.ClientBalance;
import org.springframework.data.repository.CrudRepository;

public interface ClientBalanceRepository extends CrudRepository<ClientBalance, String> {
}
