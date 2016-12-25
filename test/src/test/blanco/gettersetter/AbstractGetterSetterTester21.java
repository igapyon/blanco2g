package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester21 {
	/**
	 * 処理対象となるフィールド。
	 */
	@BlancoGetterSetter(addMethodCallToSetter = "String.valueOf")
	protected String field01 = null;

	@BlancoGetterSetter(addMethodCallToSetter = "String.valueOf")
	protected static String field02 = null;
}
