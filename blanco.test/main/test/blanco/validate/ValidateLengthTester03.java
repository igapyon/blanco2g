/*
 * このクラスは 'AbstractValidateLengthTester03' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import blanco.fw.BlancoGeneratedBy;

/**
 * 項目長さのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateLengthTester03 extends AbstractValidateLengthTester03 {
    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateLength]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01() {
        // [@BlancoValidateLength]
        {
            final String value = (field01 == null ? "" : field01.toString());
            if (value.length() == 0) {
                // 長さが 1 以上のもののみ検証対象とします。
            } else if (value.length() < 2 || value.length() > 3) {
                return "「フィールド１」は 2 文字以上 3 文字以下で入力してください。";
            }
        }
        return null;
    }
}
