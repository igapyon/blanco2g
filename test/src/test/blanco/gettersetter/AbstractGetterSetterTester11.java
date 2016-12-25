package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * static フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester11 {
    /**
     * static 処理対象となるフィールド。
     */
    @BlancoGetterSetter
    static String field01 = "あいう";
}
