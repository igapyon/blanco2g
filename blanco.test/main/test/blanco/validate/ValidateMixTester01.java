/*
 * このクラスは 'AbstractValidateMixTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import blanco.fw.BlancoGeneratedBy;
import blanco.validate.BlancoValidateRuntimeUtil;

/**
 * いろいろ混ぜたテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateMixTester01 extends AbstractValidateMixTester01 {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 処理対象となるフィールド。
     * [@BlancoValidateRequired]
     * [@BlancoValidateLength]
     *
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField02() {
        // [@BlancoValidateRequired]
        if (field02 == null || BlancoValidateRuntimeUtil.trim(field02).length() == 0) {
            return "「フィールド２」に値が入力されていません。";
        }
        // [@BlancoValidateLength]
        {
            final String value = (field02 == null ? "" : field02.toString());
            if (value.length() == 0) {
                // 長さが 1 以上のもののみ検証対象とします。
            } else if (value.length() < 3 || value.length() > 5) {
                return "「フィールド２」は 3 文字以上 5 文字以下で入力してください。";
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
    public boolean validateField02(final ActionMessages actionMessages) {
        // [@BlancoValidateRequired]
        if (field02 == null || BlancoValidateRuntimeUtil.trim(field02).length() == 0) {
            final ActionMessage msg = new ActionMessage("message.blanco.validate.required.input", "フィールド２");
            actionMessages.add("WARN", msg);
            return false;
        }
        // [@BlancoValidateLength]
        {
            final String value = (field02 == null ? "" : field02.toString());
            if (value.length() == 0) {
                // 長さが 1 以上のもののみ検証対象とします。
            } else if (value.length() < 3 || value.length() > 5) {
                final ActionMessage msg = new ActionMessage("message.blanco.validate.length.minmax", "フィールド２", "3", "5");
                actionMessages.add("WARN", msg);
                return false;
            }
        }
        return true;
    }
}
