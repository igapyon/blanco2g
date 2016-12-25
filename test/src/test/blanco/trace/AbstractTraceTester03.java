package test.blanco.trace;

import blanco.trace.BlancoTrace;

/**
 * メソッド・トレースのテスト。
 */
public abstract class AbstractTraceTester03 {
    /**
     * メソッド・トレースのテスト。
     * 
     * FIXME static メソッドを適切に扱えるようにすること。
     * 
     * @param arg01
     * @return
     */
    @BlancoTrace
    public static String hello(final String arg01) {
        return "Hello: " + arg01;
    }
}
