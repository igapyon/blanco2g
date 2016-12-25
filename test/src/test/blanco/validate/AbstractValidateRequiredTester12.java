package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateRequired;

/**
 * 必須項目フィールドのテスト。
 */
public abstract class AbstractValidateRequiredTester12 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRequired(isSelection = true)
    String field01 = "あいう";
}
