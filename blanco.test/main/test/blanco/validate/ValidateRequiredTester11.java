/*
 * このクラスは 'AbstractValidateRequiredTester11' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import blanco.fw.BlancoGeneratedBy;
import blanco.validate.BlancoValidateRuntimeUtil;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRequiredTester11 extends AbstractValidateRequiredTester11 {
    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateRequired]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01() {
        // [@BlancoValidateRequired]
        if (field01 == null || BlancoValidateRuntimeUtil.trim(field01).length() == 0) {
            return "「フィールド１」に値が入力されていません。";
        }
        return null;
    }
}
