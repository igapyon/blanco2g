package test.blanco.validate;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateRangeTester32 {
    /**
     * 処理対象となるフィールド。
     * 
     * java.lang.String の場合。
     */
    @BlancoValidateMethodForStruts
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(max = "3.3")
    String field01 = "3.2";
}
