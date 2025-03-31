package com.web3company.vault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClientBalance {

    @Id
    private String clientId;

    private double collateral;
    private double exposure;

    // Getters and Setters
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public double getCollateral() { return collateral; }
    public void setCollateral(double collateral) { this.collateral = collateral; }

    public double getExposure() { return exposure; }
    public void setExposure(double exposure) { this.exposure = exposure; }
}
