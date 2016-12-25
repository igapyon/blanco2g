/*
 * このクラスは 'AbstractValidateRangeTester13' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import blanco.fw.BlancoGeneratedBy;

/**
 * 項目長さのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRangeTester13 extends AbstractValidateRangeTester13 {
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
        } else if (field01.compareTo(new BigDecimal("3.1")) < 0 || field01.compareTo(new BigDecimal("3.3")) > 0) {
            return "「フィールド１」は 3.1 以上 3.3 以下で入力してください。";
        }
        return null;
    }

    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateMethodForStruts]
     * [@BlancoValidateRange]
     *
     * @param actionMessages Apache Struts ActionMessages
     * @return 検証結果が問題無しであれば true。検証結果に問題あれば false。
     */
    public boolean validateField01(final ActionMessages actionMessages) {
        // [@BlancoValidateRange]
        if (field01 == null) {
            // 値がセットされているもののみ検証対象とします。
        } else if (field01.compareTo(new BigDecimal("3.1")) < 0 || field01.compareTo(new BigDecimal("3.3")) > 0) {
            final ActionMessage msg = new ActionMessage("message.blanco.validate.range.minmax", "フィールド１", "3.1", "3.3");
            actionMessages.add("WARN", msg);
            return false;
        }
        return true;
    }
}
