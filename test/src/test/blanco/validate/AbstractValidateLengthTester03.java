package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateLength;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateLengthTester03 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateLength(min = 2, max = 3)
    String field01 = "あいう";
}
