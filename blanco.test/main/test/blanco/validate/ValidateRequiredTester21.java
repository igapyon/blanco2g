/*
 * このクラスは 'AbstractValidateRequiredTester21' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import blanco.fw.BlancoGeneratedBy;
import blanco.validate.BlancoValidateRuntimeUtil;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRequiredTester21 extends AbstractValidateRequiredTester21 {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateMethodForStruts]
     *
     * @param actionMessages Apache Struts ActionMessages
     * @return 検証結果が問題無しであれば true。検証結果に問題あれば false。
     */
    public boolean validateField01(final ActionMessages actionMessages) {
        // [@BlancoValidateRequired]
        if (field01 == null || BlancoValidateRuntimeUtil.trim(field01).length() == 0) {
            final ActionMessage msg = new ActionMessage("message.blanco.validate.required.input", "フィールド１");
            actionMessages.add("WARN", msg);
            return false;
        }
        return true;
    }
}
