package test.blanco.validate;

import java.math.BigDecimal;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateRangeTester11 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoValidateMethodForStruts
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(min = "3.3")
    BigDecimal field01 = new BigDecimal("3.2");
}
