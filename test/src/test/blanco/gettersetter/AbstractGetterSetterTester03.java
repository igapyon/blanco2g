package test.blanco.gettersetter;

import java.util.ArrayList;
import java.util.List;

import blanco.gettersetter.BlancoGetterSetter;

/**
 * フィールドのゲッター・セッターのテスト。
 */
public abstract class AbstractGetterSetterTester03 {
    /**
     * 処理対象となるフィールド。
     * 
     * TODO 現在、これは適切に処理できないはず。
     */
    @BlancoGetterSetter
    List<String> field01 = new ArrayList<String>();
}
