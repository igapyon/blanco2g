package test.blanco.trace;

import blanco.trace.BlancoTrace;

/**
 * メソッド・トレースのテスト。
 */
public abstract class AbstractTraceTester05 {
    /**
     * メソッド・トレースのテスト。
     * 
     * パラメータも戻り値も無い場合。
     */
    @BlancoTrace
    public void hello() {
    }
}
