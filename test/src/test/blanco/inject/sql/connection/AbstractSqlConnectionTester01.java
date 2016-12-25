package test.blanco.inject.sql.connection;

import java.sql.Connection;
import java.sql.SQLException;

import blanco.fw.BlancoInject;

/**
 * メソッド・パラメータ注入でデータベース接続を取得する例。
 */
public abstract class AbstractSqlConnectionTester01 {
    /**
     * メソッド・パラメータ注入でデータベース接続を取得する例。
     * 
     * @param conn01
     *            注入されて自動接続されたデータベースコネクション。
     * @param arg01
     *            通常のメソッド・パラメータ。
     * @throws SQLException
     *             データベース例外が発生した場合。
     */
    public void test01(@BlancoInject final Connection conn01, final String arg01) throws SQLException {
        // DO SOMETHING
    }
}
