/*
 * このクラスは 'AbstractGeneratedByBlancoDbTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.blancodb;

import java.sql.Connection;
import java.sql.SQLException;

import blanco.fw.BlancoGeneratedBy;

/**
 * blancoDb が生成したオブジェクトの注入の試験。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class GeneratedByBlancoDbTester01 extends AbstractGeneratedByBlancoDbTester01 {
    /**
     * blancoDb が生成したオブジェクトの注入の試験。
     */
    public void a01() {
        // [@BlancoInject] Begin database transaction.
        final Connection conn0 = blanco.db.BlancoDbConnectionUtil.getConnection();
        try {
            // [@BlancoInject] Inject DAO object.
            final SampleBlancoDbIterator iterator = new SampleBlancoDbIterator(conn0);
            try {
                super.a01(conn0, iterator);
            } finally {
                // [@BlancoInject] Close DAO object.
                try {
                    iterator.close();
                } catch (SQLException exIgnore) {
                }
            }
        } finally {
            // [@BlancoInject] End database transaction.
            blanco.db.BlancoDbConnectionUtil.releaseConnection(conn0);
        }
    }
}
