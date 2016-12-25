/*
 * このクラスは 'AbstractSqlConnectionTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.sql.connection;

import java.sql.Connection;
import java.sql.SQLException;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータ注入でデータベース接続を取得する例。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class SqlConnectionTester01 extends AbstractSqlConnectionTester01 {
    /**
     * メソッド・パラメータ注入でデータベース接続を取得する例。
     *
     * @param arg01 通常のメソッド・パラメータ。
     * @throws SQLException データベース例外が発生した場合。
     */
    public void test01(String arg01) throws SQLException {
        // [@BlancoInject] Begin database transaction.
        final Connection conn01 = blanco.db.BlancoDbConnectionUtil.getConnection();
        try {
            super.test01(conn01, arg01);
        } finally {
            // [@BlancoInject] End database transaction.
            blanco.db.BlancoDbConnectionUtil.releaseConnection(conn01);
        }
    }
}
