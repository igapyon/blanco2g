package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.struts.BlancoStrutsForm;
import blanco.validate.BlancoValidateMethodForJsf;
import blanco.validate.BlancoValidateRequired;

/**
 * 必須項目フィールドのテスト。
 */
@BlancoStrutsForm
public abstract class AbstractValidateRequiredTester31 {
    /**
     * 処理対象となるフィールド。
     * 
     * JSF 版
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateMethodForJsf
    @BlancoValidateRequired
    String field01 = "あいう";
}
