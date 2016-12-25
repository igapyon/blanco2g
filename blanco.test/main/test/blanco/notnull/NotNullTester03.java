/*
 * このクラスは 'AbstractNotNullTester03' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.notnull;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class NotNullTester03 extends AbstractNotNullTester03 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。
     *
     * @param arg01 非 NULL が期待されるパラメータ。
     * @param argNullable01 NULL が入っても問題のないパラメータ。
     * @return  戻り値のあるメソッドのテスト。
     */
    public String test(String arg01, String argNullable01) {
        java.lang.String autoValMethodResult;
        if (arg01 == null) {
            throw new IllegalArgumentException("不正引数例外: NotNullTester03#test(String arg01, String argNullable01) のパラメータ 'arg01' に null を与えることはできません。");
        }
        autoValMethodResult = super.test(arg01, argNullable01);
        return autoValMethodResult;
    }
}
