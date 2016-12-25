package test.blanco.struts;

import org.apache.struts.action.ActionForm;

import blanco.gettersetter.BlancoGetterSetter;
import blanco.struts.BlancoStrutsForm;

/**
 * テスト用のアクションフォーム。
 */
@BlancoStrutsForm
public abstract class AbstractStrutsTester12Form extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * フィールドその1
     */
    @BlancoGetterSetter
    protected String field1 = "abc";
}
