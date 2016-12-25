package test.blanco.notnull;

import blanco.notnull.BlancoNotNull;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
public abstract class AbstractNotNullTester02 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     * 
     * @param arg01
     *            非 NULL が期待されるパラメータ。
     * @param argNullable01
     *            NULL が入っても問題のないパラメータ。
     */
    public void test(@BlancoNotNull final String arg01, final String argNullable01) {
        System.out.println("Hello: " + arg01 + ", " + argNullable01);
    }
}
