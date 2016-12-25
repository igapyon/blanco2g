package test.blanco.trace;

import blanco.trace.BlancoTrace;

/**
 * メソッド・トレースのテスト。
 */
public abstract class AbstractTraceTester02 {
    /**
     * メソッド・トレースのテスト。
     * 
     * @param arg01
     * @return
     */
    @BlancoTrace
    public String hello(final String arg01) {
        return "Hello: " + arg01;
    }
}
