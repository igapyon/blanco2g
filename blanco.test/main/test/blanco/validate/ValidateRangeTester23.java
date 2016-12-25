/*
 * このクラスは 'AbstractValidateRangeTester23' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import java.math.BigDecimal;

import blanco.fw.BlancoGeneratedBy;

/**
 * 項目長さのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRangeTester23 extends AbstractValidateRangeTester23 {
    /**
     * 処理対象となるフィールド。
     * java.lang.String の場合
     * [@BlancoValidateRange]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01() {
        // [@BlancoValidateRange]
        if (field01 == null || field01.length() == 0) {
            // 値がセットされているもののみ検証対象とします。
        } else if (new BigDecimal(field01).compareTo(new BigDecimal("3.1")) < 0 || new BigDecimal(field01).compareTo(new BigDecimal("3.3")) > 0) {
            return "「フィールド１」は 3.1 以上 3.3 以下で入力してください。";
        }
        return null;
    }
}
