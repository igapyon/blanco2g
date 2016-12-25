package test.blanco.validate;

import blanco.validate.BlancoValidateRequired;

/**
 * 必須項目フィールドのテスト。
 */
public abstract class AbstractValidateRequiredTester02 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoValidateRequired(isSelection = true)
    String field01 = "あいう";
}
