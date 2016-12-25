package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateRangeTester33 {
    /**
     * 処理対象となるフィールド。
     * 
     * java.lang.String の場合。
     */
    @BlancoValidateMethodForStruts
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(min = "3.1", max = "3.3")
    String field01 = "3.2";
}
