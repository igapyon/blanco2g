/*
 * このクラスは 'AbstractValidateRequiredTester31' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.validate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;
import blanco.validate.BlancoValidateRuntimeUtil;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class ValidateRequiredTester31 extends AbstractValidateRequiredTester31 {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 処理対象となるフィールド。
     * JSF 版
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
     * JSF 版
     * [@BlancoValidateMethodForJsf]
     *
     * @param ctx JSF Messages
     * @return 検証結果が問題無しであれば true。検証結果に問題あれば false。
     */
    public boolean validateField01(final FacesContext ctx) {
        // [@BlancoValidateRequired]
        if (validateField01() != null) {
            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, validateField01(), null);
            ctx.addMessage(null, msg);
            return false;
        }
        return true;
    }
}
