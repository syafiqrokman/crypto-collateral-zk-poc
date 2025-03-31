package com.web3company.vault.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CTREngineTest {

    private final CTREngine ctrEngine = new CTREngine();

    @Test
    public void testCtrCalculation() {
        double ctr = ctrEngine.calculateCTR(5000, 2500);
        assertEquals(200.0, ctr);
    }

    @Test
    public void testCtrZeroExposure() {
        double ctr = ctrEngine.calculateCTR(1000, 0);
        assertEquals(100.0, ctr);
    }

    @Test
    public void testLiquidationTrigger() {
        assertTrue(ctrEngine.isLiquidationRequired(90, 100));
        assertFalse(ctrEngine.isLiquidationRequired(110, 100));
    }
}
