/*
 * このクラスは 'AbstractNotNullTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.notnull;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class NotNullTester01 extends AbstractNotNullTester01 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     *
     * @param arg01 非 NULL が期待されるパラメータ。
     */
    public void test(String arg01) {
        if (arg01 == null) {
            throw new IllegalArgumentException("不正引数例外: NotNullTester01#test(String arg01) のパラメータ 'arg01' に null を与えることはできません。");
        }
        super.test(arg01);
    }
}
