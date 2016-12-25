package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester04 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoGetterSetter(getter = false, setter = true)
    protected String field01 = "あいう";
}
