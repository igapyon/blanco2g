/*
 * このクラスは 'AbstractValidateRequiredTester12' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import blanco.fw.BlancoGeneratedBy;
import blanco.validate.BlancoValidateRuntimeUtil;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRequiredTester12 extends AbstractValidateRequiredTester12 {
    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateRequired]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01() {
        // [@BlancoValidateRequired]
        if (field01 == null || BlancoValidateRuntimeUtil.trim(field01).length() == 0) {
            return "「フィールド１」を選択してください。";
        }
        return null;
    }
}
