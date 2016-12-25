/*
 * このクラスは 'AbstractStrutsTester01Form' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * テスト用のアクションフォーム。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester01Form extends AbstractStrutsTester01Form {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * リセット
     *
     * @param mapping アクション・マッピング.
     * @param request リクエスト.
     */
    public void reset(final ActionMapping mapping, final HttpServletRequest request) {
        field1 = "abc";
        field2 = 123;
    }

    /**
     * フィールドその1
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public String getField1() {
        return field1;
    }

    /**
     * フィールドその1
     * [@BlancoGetterSetter]
     *
     * @param field1 設定したい値。
     */
    public void setField1(final String field1) {
        this.field1 = field1;
    }

    /**
     * フィールドその2
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public int getField2() {
        return field2;
    }

    /**
     * フィールドその2
     * [@BlancoGetterSetter]
     *
     * @param field2 設定したい値。
     */
    public void setField2(final int field2) {
        this.field2 = field2;
    }

    /**
     * 初期化子をもたないフィールド。
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public String getField3() {
        return field3;
    }

    /**
     * 初期化子をもたないフィールド。
     * [@BlancoGetterSetter]
     *
     * @param field3 設定したい値。
     */
    public void setField3(final String field3) {
        this.field3 = field3;
    }
}
