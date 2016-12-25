package test.blanco.driver;

import blanco.driver.BlancoDriver;

/**
 * Driver のテスト。
 */
public abstract class AbstractDriverTester02 {
    @BlancoDriver(className = "test.blanco.driver.DummyDriverTester")
    final int aaa = 0;
}
