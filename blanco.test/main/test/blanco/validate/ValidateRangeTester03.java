/*
 * このクラスは 'AbstractValidateRangeTester03' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import java.math.BigDecimal;

import blanco.fw.BlancoGeneratedBy;

/**
 * 項目長さのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRangeTester03 extends AbstractValidateRangeTester03 {
    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateRange]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01() {
        // [@BlancoValidateRange]
        if (field01 == null) {
            // 値がセットされているもののみ検証対象とします。
        } else if (field01.compareTo(new BigDecimal("3.3")) < 0 || field01.compareTo(new BigDecimal("5.6")) > 0) {
            return "「フィールド１」は 3.3 以上 5.6 以下で入力してください。";
        }
        return null;
    }
}
