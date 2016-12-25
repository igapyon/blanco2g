package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester02 {
    /**
     * 処理対象となるフィールド。
     * 
     * TODO 現在、これは適切に処理できないはず。
     */
    @BlancoGetterSetter
    static String field01 = "あいう";
}
