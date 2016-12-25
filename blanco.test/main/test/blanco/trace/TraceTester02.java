/*
 * このクラスは 'AbstractTraceTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester02 extends AbstractTraceTester02 {
    /**
     * メソッド・トレースのテスト。
     *
     * @param arg01
     * @return
     */
    public String hello(String arg01) {
        java.lang.String autoValMethodResult;
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester02#hello(" + arg01 + ") begin.");
        autoValMethodResult = super.hello(arg01);
        System.out.println("trace: AbstractTraceTester02#hello(" + arg01 + ") end. " + "[" + autoValMethodResult + "] " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
        return autoValMethodResult;
    }
}
