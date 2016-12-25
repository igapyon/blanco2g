package test.blanco.validate;

import java.math.BigDecimal;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateRangeTester02 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(max = "3.3")
    BigDecimal field01 = new BigDecimal("3.2");
}
