/*
 * このクラスは 'AbstractNotNullTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.notnull;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class NotNullTester02 extends AbstractNotNullTester02 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     *
     * @param arg01 非 NULL が期待されるパラメータ。
     * @param argNullable01 NULL が入っても問題のないパラメータ。
     */
    public void test(String arg01, String argNullable01) {
        if (arg01 == null) {
            throw new IllegalArgumentException("不正引数例外: NotNullTester02#test(String arg01, String argNullable01) のパラメータ 'arg01' に null を与えることはできません。");
        }
        super.test(arg01, argNullable01);
    }
}
