package test.blanco.validate;

import java.math.BigDecimal;

import blanco.driver.BlancoDriver;
import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
@BlancoDriver(className = "blanco.validate.driver.Blanco2gValidateRangeDriver")
public abstract class AbstractValidateRangeTester01 {
    /**
     * 処理対象となるフィールド。
     * 
     * java.math.BigDecimal の場合。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(min = "3.3")
    BigDecimal field01 = new BigDecimal("3.2");
}
