package test.blanco.struts;

import org.apache.struts.action.ActionForm;

import blanco.gettersetter.BlancoGetterSetter;
import blanco.struts.BlancoStrutsForm;
import blanco.struts.BlancoStrutsReset;

/**
 * テスト用のアクションフォーム。
 */
@BlancoStrutsForm
public abstract class AbstractStrutsTester01Form extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * フィールドその1
     */
    @BlancoGetterSetter
    @BlancoStrutsReset
    protected String field1 = "abc";

    /**
     * フィールドその2
     */
    @BlancoGetterSetter
    @BlancoStrutsReset
    protected int field2 = 123;

    /**
     * 初期化子をもたないフィールド。
     */
    @BlancoGetterSetter
    protected String field3;
}
