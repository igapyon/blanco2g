package test.blanco.struts;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoInject;
import blanco.struts.BlancoStrutsForward;
import blanco.struts.BlancoStrutsLogic;

/**
 * Struts テスト用のロジック・クラス。
 */
@BlancoStrutsLogic(path = "/hello")
public abstract class AbstractStrutsTester01Logic {
    /**
     * 正常終了した場合のフォワード先。
     */
    @BlancoStrutsForward(path = "/pages/hello.jsp")
    static final String FORWARD_SUCCESS = "success";

    /**
     * ビジネスロジックのエントリ・ポイント。
     * 
     * @param mapping
     *            アクション・マッピング
     * @param form
     *            フォーム。
     * @param request
     *            リクエスト。
     * @param response
     *            レスポンス。
     * @param conn
     *            データベース接続。
     * @return 実行結果文字列。
     * @throws Exception
     *             例外が発生した場合。
     */
    public String execute(final ActionMapping mapping, final StrutsTester01Form form, final HttpServletRequest request,
            final HttpServletResponse response, @BlancoInject final Connection conn) throws Exception {
        return FORWARD_SUCCESS;
    }
}
