package test.blanco.validate;

import java.math.BigDecimal;

import blanco.name.BlancoLogicalName;
import blanco.validate.BlancoValidateMessage;
import blanco.validate.BlancoValidateMethodForStruts;
import blanco.validate.BlancoValidateRange;

/**
 * 項目長さのテスト。
 */
public abstract class AbstractValidateRangeTester45 {
	/**
	 * 処理対象となるフィールド。
	 * 
	 * java.lang.String の場合。
	 */
	@BlancoValidateMethodForStruts
	@BlancoLogicalName(name = "フィールド１")
	@BlancoValidateRange(max = "3.3")
	@BlancoValidateMessage(useLocationInfo = true)
	BigDecimal field01 = new BigDecimal("3.2");
}
