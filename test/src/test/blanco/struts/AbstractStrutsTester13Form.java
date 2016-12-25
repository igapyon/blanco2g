package test.blanco.struts;

import java.util.List;

import org.apache.struts.action.ActionForm;

import blanco.gettersetter.BlancoGetterSetter;
import blanco.struts.BlancoStrutsForm;
import blanco.struts.BlancoStrutsReset;

/**
 * テスト用のアクションフォーム。
 */
@BlancoStrutsForm
public abstract class AbstractStrutsTester13Form extends ActionForm {
    private static final long serialVersionUID = 1L;

    /**
     * フィールドその1
     * 
     * FIXME java.util.ArrayList と FQCN で書いているが、こうではなく省略して記述できるようにしたい。
     */
    @BlancoGetterSetter
    @BlancoStrutsReset
    protected List<String> field1 = new java.util.ArrayList<String>();
}
