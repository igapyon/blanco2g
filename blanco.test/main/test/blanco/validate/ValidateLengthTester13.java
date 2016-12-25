/*
 * このクラスは 'AbstractValidateLengthTester13' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import blanco.fw.BlancoGeneratedBy;

/**
 * 項目長さのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateLengthTester13 extends AbstractValidateLengthTester13 {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateMethodForStruts]
     * [@BlancoValidateLength]
     *
     * @param actionMessages Apache Struts ActionMessages
     * @return 検証結果が問題無しであれば true。検証結果に問題あれば false。
     */
    public boolean validateField01(final ActionMessages actionMessages) {
        // [@BlancoValidateLength]
        {
            final String value = (field01 == null ? "" : field01.toString());
            if (value.length() == 0) {
                // 長さが 1 以上のもののみ検証対象とします。
            } else if (value.length() < 2 || value.length() > 3) {
                final ActionMessage msg = new ActionMessage("message.blanco.validate.length.minmax", "フィールド１", "2", "3");
                actionMessages.add("WARN", msg);
                return false;
            }
        }
        return true;
    }
}
