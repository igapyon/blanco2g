/*
 * このクラスは 'AbstractStrutsTester03Logic' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * Struts のテスト。
 * request スコープの試験。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester03Action extends Action {
    /**
     * アクション
     *
     * @param mapping action mapping.
     * @param form action form.
     * @param request request.
     * @param response response.
     * @return 戻り値。
     * @throws Exception 例外
     */
    @Override
    public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final StrutsTester03Logic logic = new StrutsTester03Logic();
        return mapping.findForward(logic.execute(mapping, (test.blanco.struts.StrutsTester01Form) form, request, response));
    }
}
