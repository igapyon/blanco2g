/*
 * このクラスは 'AbstractTraceTester03' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester03 extends AbstractTraceTester03 {
    /**
     * メソッド・トレースのテスト。
     * FIXME static メソッドを適切に扱えるようにすること。
     *
     * @param arg01
     * @return
     */
    public static String hello(String arg01) {
        java.lang.String autoValMethodResult;
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester03#hello(" + arg01 + ") begin.");
        autoValMethodResult = AbstractTraceTester03.hello(arg01);
        System.out.println("trace: AbstractTraceTester03#hello(" + arg01 + ") end. " + "[" + autoValMethodResult + "] " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
        return autoValMethodResult;
    }
}
