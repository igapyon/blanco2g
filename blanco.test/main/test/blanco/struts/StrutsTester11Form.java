/*
 * このクラスは 'AbstractStrutsTester11Form' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * テスト用のアクションフォーム。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester11Form extends AbstractStrutsTester11Form {
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
}
