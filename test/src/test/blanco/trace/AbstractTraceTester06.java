package test.blanco.trace;

import blanco.trace.BlancoTrace;

/**
 * private メソッドは処理してはなりません。
 */
public abstract class AbstractTraceTester06 {
    /**
     * private メソッドは処理してはなりません。
     */
    @BlancoTrace
    /* private */void hello() {
    }
}
