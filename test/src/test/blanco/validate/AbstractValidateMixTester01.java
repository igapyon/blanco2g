package test.blanco.validate;

import org.apache.struts.action.ActionForm;

import blanco.name.BlancoLogicalName;
import blanco.struts.BlancoStrutsForm;
import blanco.validate.BlancoValidateLength;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRequired;

/**
 * いろいろ混ぜたテスト。
 */
@BlancoStrutsForm
public abstract class AbstractValidateMixTester01 extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * 処理対象となるフィールド。
     */
    @BlancoLogicalName(name = "フィールド２")
    @BlancoValidateMethodForStruts
    @BlancoValidateRequired
    @BlancoValidateLength(min = 3, max = 5)
    String field02 = "あいう";
}
