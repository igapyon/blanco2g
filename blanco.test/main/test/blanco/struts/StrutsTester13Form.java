/*
 * このクラスは 'AbstractStrutsTester13Form' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * テスト用のアクションフォーム。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester13Form extends AbstractStrutsTester13Form {
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
        field1 = new java.util.ArrayList<String>();
    }

    /**
     * フィールドその1
     * FIXME java.util.ArrayList と FQCN で書いているが、こうではなく省略して記述できるようにしたい。
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public List<java.lang.String> getField1() {
        return field1;
    }

    /**
     * フィールドその1
     * FIXME java.util.ArrayList と FQCN で書いているが、こうではなく省略して記述できるようにしたい。
     * [@BlancoGetterSetter]
     *
     * @param field1 設定したい値。
     */
    public void setField1(final List<java.lang.String> field1) {
        this.field1 = field1;
    }
}
