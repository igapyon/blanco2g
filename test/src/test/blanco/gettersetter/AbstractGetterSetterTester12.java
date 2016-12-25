package test.blanco.gettersetter;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * private フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester12 {
    /**
     * 処理対象となるフィールド。
     * 
     * これは生成対象外にする必要あり。
     */
    @SuppressWarnings("unused")
    @BlancoGetterSetter
    private String field01 = "あいう";
}
