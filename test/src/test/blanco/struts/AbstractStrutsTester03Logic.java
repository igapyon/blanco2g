package test.blanco.struts;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoInject;
import blanco.struts.BlancoStrutsForward;
import blanco.struts.BlancoStrutsLogic;

/**
 * Struts のテスト。
 * 
 * request スコープの試験。
 */
@BlancoStrutsLogic(path = "/aaa", scope = "request")
public abstract class AbstractStrutsTester03Logic {
    /**
     * 正常終了の遷移先。
     */
    @BlancoStrutsForward(path = "/pages/hello.jsp")
    static final String FORWARD_SUCCESS = "success";

    /**
     * 異常終了の遷移先。
     */
    @BlancoStrutsForward(path = "/pages/error.jsp", redirect = true)
    static final String FORWARD_ERROR = "error";

    /**
     * ビジネスロジック
     */
    public String execute(final ActionMapping mapping, final StrutsTester01Form form, final HttpServletRequest request,
            final HttpServletResponse response, @BlancoInject final Connection conn) throws Exception {
        return FORWARD_ERROR;
    }
}
