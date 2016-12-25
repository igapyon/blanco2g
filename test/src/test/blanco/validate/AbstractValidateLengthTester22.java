package test.blanco.validate;

import org.apache.struts.action.ActionForm;

import blanco.name.BlancoLogicalName;
import blanco.struts.BlancoStrutsForm;
import blanco.validate.BlancoValidateLength;
import blanco.validate.BlancoValidateMessage;
import blanco.validate.BlancoValidateMethodForStruts;

/**
 * 項目長さのテスト。
 */
@BlancoStrutsForm
public abstract class AbstractValidateLengthTester22 extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateMethodForStruts
    @BlancoValidateLength(max = 3)
	@BlancoValidateMessage(useLocationInfo = true)
    String field01 = "あいう";
}
