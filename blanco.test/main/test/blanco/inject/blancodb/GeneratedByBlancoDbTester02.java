/*
 * このクラスは 'AbstractGeneratedByBlancoDbTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.blancodb;

import java.sql.Connection;
import java.sql.SQLException;

import blanco.fw.BlancoGeneratedBy;

/**
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class GeneratedByBlancoDbTester02 extends AbstractGeneratedByBlancoDbTester02 {
    /**
     *
     * @param conn0
     */
    public void a01(Connection conn0) {
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
    }
}
