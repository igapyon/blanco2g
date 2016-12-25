package test.blanco.notnull;

import blanco.notnull.BlancoNotNull;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
public abstract class AbstractNotNullTester01 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     * 
     * @param arg01
     *            非 NULL が期待されるパラメータ。
     */
    public void test(@BlancoNotNull final String arg01) {
        System.out.println("Hello: " + arg01);
    }
}
