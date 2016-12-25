package test.blanco.notnull;

import blanco.notnull.BlancoNotNull;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
public abstract class AbstractNotNullTester03 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     * 
     * @param arg01
     *            非 NULL が期待されるパラメータ。
     * @param argNullable01
     *            NULL が入っても問題のないパラメータ。
     * @return 戻り値のあるメソッドのテスト。
     */
    public String test(@BlancoNotNull final String arg01, final String argNullable01) {
        return "Hello: " + arg01 + ", " + argNullable01;
    }
}
