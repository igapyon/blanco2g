package test.blanco.validate;

import blanco.validate.BlancoValidateRequired;

/**
 * 必須項目フィールドのテスト。
 */
public abstract class AbstractValidateRequiredTester01 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoValidateRequired
    String field01 = "あいう";
}
