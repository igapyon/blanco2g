/*
 * このクラスは 'AbstractTraceTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester01 extends AbstractTraceTester01 {
    /**
     * メソッド・トレースのテスト。
     *
     * @param arg01
     */
    public void hello(String arg01) {
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester01#hello(" + arg01 + ") begin.");
        super.hello(arg01);
        System.out.println("trace: AbstractTraceTester01#hello(" + arg01 + ") end. " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
    }
}
