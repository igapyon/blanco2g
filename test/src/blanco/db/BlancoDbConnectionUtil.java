/*******************************************************************************
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2011 Toshiki IGA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Toshiki IGA - initial API and implementation
 *******************************************************************************/
package blanco.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * データベース接続の取得・解放のためのクラス。
 */
public class BlancoDbConnectionUtil {
    /**
     * JAVA EE のデータベースを利用するかどうか。
     */
    private static final boolean IS_JAVAEE = false;

    /**
     * データベース接続の取得。
     * 
     * @return
     */
    public static Connection getConnection() {
        try {
            if (!IS_JAVAEE) {
                // JDBC Driver
                // PostgreSQL の場合の例です。
                try {
                    Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException ex) {
                    throw new IllegalArgumentException("Unexpected error occurred.", ex);
                }

                final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres",
                        "password");
                conn.setAutoCommit(false);
                return conn;
            } else {
                // JAVA EE
                Context context = null;
                try {
                    context = new InitialContext();
                } catch (NamingException ex) {
                    throw new IllegalArgumentException("Unexpected error occurred.", ex);
                }

                try {
                    // TODO 「DataSource」キャッシュの考慮。
                    final DataSource ds = (DataSource) context.lookup("jdbc/mydb");
                    final Connection conn = ds.getConnection();
                    conn.setAutoCommit(false);
                    return conn;
                } catch (NamingException ex) {
                    throw new IllegalArgumentException("Unexpected error occurred.", ex);
                }
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Unexpected error occurred.", ex);
        }
    }

    /**
     * データベース接続の開放。
     * 
     * @param conn
     */
    public static void releaseConnection(final Connection conn) {
        try {
            if (conn != null)
                conn.rollback();
        } catch (SQLException exIgnore) {
            System.out.println("Unexpected error occurred.: " + exIgnore.toString());
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Unexpected error occurred.", ex);
        }
    }
}
