/*
 * このクラスは 'AbstractValidateRangeTester42' の具象クラスとして blanco Framework によって自動生成されました。
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
public class ValidateRangeTester42 extends AbstractValidateRangeTester42 {
    /**
     * 処理対象となるフィールド。
     * java.lang.String の場合。
     * [@BlancoValidateRange]
     *
     * @param locationInfo [@BlancoValidateMessage] メッセージの位置情報 (useLocationInfo = true)
     * @return 検証結果の文字列。問題なければ null。
     */
    public String validateField01(final String locationInfo) {
        // [@BlancoValidateRange]
        if (field01 == null || field01.length() == 0) {
            // 値がセットされているもののみ検証対象とします。
        } else if (new BigDecimal(field01).compareTo(new BigDecimal("3.3")) > 0) {
            return locationInfo + "「フィールド１」は 3.3 以下で入力してください。";
        }
        return null;
    }

    /**
     * 処理対象となるフィールド。
     * java.lang.String の場合。
     * [@BlancoValidateMethodForStruts]
     * [@BlancoValidateRange]
     *
     * @param actionMessages Apache Struts ActionMessages
     * @param locationInfo [@BlancoValidateMessage] メッセージの位置情報 (useLocationInfo = true)
     * @return 検証結果が問題無しであれば true。検証結果に問題あれば false。
     */
    public boolean validateField01(final ActionMessages actionMessages, final String locationInfo) {
        // [@BlancoValidateRange]
        if (field01 == null || field01.length() == 0) {
            // 値がセットされているもののみ検証対象とします。
        } else if (new BigDecimal(field01).compareTo(new BigDecimal("3.3")) > 0) {
            final ActionMessage msg = new ActionMessage("message.blanco.validate.range.max.locationinfo", locationInfo, "フィールド１", "3.3");
            actionMessages.add("WARN", msg);
            return false;
        }
        return true;
    }
}
