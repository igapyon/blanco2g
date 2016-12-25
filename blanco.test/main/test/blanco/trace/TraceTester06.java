/*
 * このクラスは 'AbstractTraceTester06' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * private メソッドは処理してはなりません。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester06 extends AbstractTraceTester06 {
    /**
     * private メソッドは処理してはなりません。
     */
    public void hello() {
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester06#hello(" + ") begin.");
        super.hello();
        System.out.println("trace: AbstractTraceTester06#hello(" + ") end. " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
    }
}
