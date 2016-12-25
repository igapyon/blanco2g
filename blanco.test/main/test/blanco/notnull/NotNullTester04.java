/*
 * このクラスは 'AbstractNotNullTester04' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.notnull;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータの非 NULL チェックのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class NotNullTester04 extends AbstractNotNullTester04 {
    /**
     * メソッド・パラメータの非 NULL チェックのテスト。スタティック・メソッド版。
     * FIXME 現在、この自動生成は正しく動作しません。
     *
     * @param arg01 非 NULL が期待されるパラメータ。
     * @param argNullable01 NULL が入っても問題のないパラメータ。
     * @return  戻り値のあるメソッドのテスト。
     */
    public static String test(String arg01, String argNullable01) {
        java.lang.String autoValMethodResult;
        if (arg01 == null) {
            throw new IllegalArgumentException("不正引数例外: NotNullTester04#test(String arg01, String argNullable01) のパラメータ 'arg01' に null を与えることはできません。");
        }
        autoValMethodResult = AbstractNotNullTester04.test(arg01, argNullable01);
        return autoValMethodResult;
    }
}
