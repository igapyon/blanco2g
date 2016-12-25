package test.blanco.struts;

import org.apache.struts.action.ActionForm;

import blanco.gettersetter.BlancoGetterSetter;
import blanco.struts.BlancoStrutsForm;
import blanco.struts.BlancoStrutsReset;

/**
 * テスト用のアクションフォーム。
 */
@BlancoStrutsForm
public abstract class AbstractStrutsTester11Form extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * フィールドその1
     */
    @BlancoGetterSetter
    @BlancoStrutsReset
    protected String field1 = "abc";
}
