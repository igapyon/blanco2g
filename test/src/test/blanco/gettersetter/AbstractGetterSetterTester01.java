package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester01 {
    /**
     * 処理対象となるフィールド。
     */
    @BlancoGetterSetter
    String field01 = "あいう";
}
