/*
 * このクラスは 'AbstractTraceTester05' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester05 extends AbstractTraceTester05 {
    /**
     * メソッド・トレースのテスト。
     * パラメータも戻り値も無い場合。
     */
    public void hello() {
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester05#hello(" + ") begin.");
        super.hello();
        System.out.println("trace: AbstractTraceTester05#hello(" + ") end. " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
    }
}
