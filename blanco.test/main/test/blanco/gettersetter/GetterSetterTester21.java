/*
 * このクラスは 'AbstractGetterSetterTester21' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.gettersetter;

import blanco.fw.BlancoGeneratedBy;

/**
 * フィールドのゲッター・セッターのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class GetterSetterTester21 extends AbstractGetterSetterTester21 {
    /**
     * 処理対象となるフィールド。
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public String getField01() {
        return field01;
    }

    /**
     * 処理対象となるフィールド。
     * [@BlancoGetterSetter]
     *
     * @param field01 設定したい値。
     */
    public void setField01(final String field01) {
        this.field01 = field01;
        this.field01 = String.valueOf(this.field01);
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public static String getField02() {
        return field02;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param argfield02 設定したい値。
     */
    public static void setField02(final String argfield02) {
        field02 = argfield02;
        field02 = String.valueOf(field02);
    }
}
