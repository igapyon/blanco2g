package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateLength;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateLengthTester02 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateLength(max = 3)
    String field01 = "あいう";
}
