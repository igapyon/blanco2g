/*
 * このクラスは 'AbstractStrutsTester01Logic' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * Struts テスト用のロジック・クラス。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester01Logic extends AbstractStrutsTester01Logic {
    /**
     * ビジネスロジックのエントリ・ポイント。
     *
     * @param mapping アクション・マッピング
     * @param form フォーム。
     * @param request リクエスト。
     * @param response レスポンス。
     * @return  実行結果文字列。
     * @throws Exception 例外が発生した場合。
     */
    public String execute(ActionMapping mapping, StrutsTester01Form form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        java.lang.String autoValMethodResult;
        // [@BlancoInject] Begin database transaction.
        final Connection conn = blanco.db.BlancoDbConnectionUtil.getConnection();
        try {
            autoValMethodResult = super.execute(mapping, form, request, response, conn);
        } finally {
            // [@BlancoInject] End database transaction.
            blanco.db.BlancoDbConnectionUtil.releaseConnection(conn);
        }
        return autoValMethodResult;
    }
}
