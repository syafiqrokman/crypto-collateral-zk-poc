package com.web3company.vault.logic;

import org.springframework.stereotype.Component;

@Component
public class CTREngine {

    public double calculateCTR(double collateral, double exposure) {
        if (exposure == 0) return 100.0;
        return (collateral / exposure) * 100;
    }

    public boolean isLiquidationRequired(double ctr, double threshold) {
        return ctr < threshold;
    }
}
