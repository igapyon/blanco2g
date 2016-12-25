package test.blanco.driver;

import blanco.driver.BlancoDriver;

/**
 * Driver のテスト。
 */
public abstract class AbstractDriverTester03 {
    /**
     * Driver のテスト。
     * 
     * @param arg01
     * @return
     */
    @BlancoDriver(className = "test.blanco.driver.DummyDriverTester")
    public String hello(final String arg01) {
        return "Hello: " + arg01;
    }
}
