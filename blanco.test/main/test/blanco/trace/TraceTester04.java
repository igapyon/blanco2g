/*
 * このクラスは 'AbstractTraceTester04' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import java.util.List;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceTester04 extends AbstractTraceTester04 {
    /**
     * メソッド・トレースのテスト。
     * FIXME 配列を適切に扱えるようにすること。
     *
     * @param arg01
     * @return
     */
    public List<java.lang.String> hello(List<java.lang.String> arg01) {
        java.util.List<java.lang.String> autoValMethodResult;
        long autoValStartTimeMillisec = System.currentTimeMillis();
        System.out.println("trace: AbstractTraceTester04#hello(" + arg01 + ") begin.");
        autoValMethodResult = super.hello(arg01);
        System.out.println("trace: AbstractTraceTester04#hello(" + arg01 + ") end. " + "[" + autoValMethodResult + "] " + (System.currentTimeMillis() - autoValStartTimeMillisec) + "ms");
        return autoValMethodResult;
    }
}
