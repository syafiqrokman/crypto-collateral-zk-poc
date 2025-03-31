package com.web3company.vault.model;

public class WithdrawalRequest {
    private String withdrawalId;
    private String clientId;
    private double amount;
    private String status; // e.g., PENDING, COMPLETED, FAILED

    // Getters and Setters
    public String getWithdrawalId() { return withdrawalId; }
    public void setWithdrawalId(String withdrawalId) { this.withdrawalId = withdrawalId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "WithdrawalRequest{" +
                "withdrawalId='" + withdrawalId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
