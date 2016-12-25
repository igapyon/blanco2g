/*
 * このクラスは 'AbstractStrutsTester03Logic' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.struts;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;

import blanco.fw.BlancoGeneratedBy;

/**
 * Struts のテスト。
 * request スコープの試験。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class StrutsTester03Logic extends AbstractStrutsTester03Logic {
    /**
     * ビジネスロジック
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
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
