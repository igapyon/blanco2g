package test.blanco.trace;

import java.util.List;

import blanco.trace.BlancoTrace;

/**
 * メソッド・トレースのテスト。
 */
public abstract class AbstractTraceTester04 {
    /**
     * メソッド・トレースのテスト。
     * 
     * FIXME 配列を適切に扱えるようにすること。
     * 
     * @param arg01
     * @return
     */
    @BlancoTrace
    public List<String> hello(final List<String> arg01) {
        return arg01;
    }
}
