package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester05 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoGetterSetter(getter = true, setter = false)
    protected String field01 = "あいう";
}
