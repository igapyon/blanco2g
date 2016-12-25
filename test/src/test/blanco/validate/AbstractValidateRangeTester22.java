package test.blanco.validate;

import blanco.driver.BlancoDriver;
import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
@BlancoDriver(className = "blanco.validate.driver.Blanco2gValidateRangeDriver")
public abstract class AbstractValidateRangeTester22 {
    /**
     * 処理対象となるフィールド。
     * 
     * java.lang.String の場合
     */
    @BlancoLogicalName(name = "フィールド１")
    @BlancoValidateRange(max = "3.3")
    String field01 = "3.2";
}
