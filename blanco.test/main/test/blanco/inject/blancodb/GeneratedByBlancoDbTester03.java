/*
 * このクラスは 'AbstractGeneratedByBlancoDbTester03' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.blancodb;

import java.sql.SQLException;

import blanco.fw.BlancoGeneratedBy;

/**
 * FIXME Connection が無い場合に警告を発すべきです。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class GeneratedByBlancoDbTester03 extends AbstractGeneratedByBlancoDbTester03 {
    /**
     * FIXME Connection が無い場合に警告を発すべきです。
     */
    public void a01() {
        // [@BlancoInject] Inject DAO object.
        final SampleBlancoDbIterator iterator = new SampleBlancoDbIterator(null);
        try {
            super.a01(iterator);
        } finally {
            // [@BlancoInject] Close DAO object.
            try {
                iterator.close();
            } catch (SQLException exIgnore) {
            }
        }
    }
}
