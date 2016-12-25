package test.blanco.driver;

import blanco.driver.BlancoDriver;
import blanco.setting.BlancoGlobalSetting;

/**
 * Driver のテスト。
 */
@BlancoDriver(className = "blanco.nothing.driver.Blanco2gNothingDriver")
@BlancoGlobalSetting(key = "blanco.blanco2g.Blanco2gDriverManager.debug", value = "true")
public abstract class AbstractDriverTester11 {
}
