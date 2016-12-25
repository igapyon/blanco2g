package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.struts.BlancoStrutsForm;
import blanco.validate.BlancoValidateMessage;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRequired;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoStrutsForm
public abstract class AbstractValidateRequiredTester41 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateMethodForStruts
    @BlancoValidateRequired
	@BlancoValidateMessage(useLocationInfo = true)
    String field01 = "あいう";
}
