package test.blanco.trace;

import blanco.trace.BlancoTrace;

/**
 * メソッド・トレースのテスト。
 */
public abstract class AbstractTraceTester01 {
    /**
     * メソッド・トレースのテスト。
     * 
     * @param arg01
     */
    @BlancoTrace
    public void hello(final String arg01) {
        System.out.println("Hello: " + arg01);
    }
}
