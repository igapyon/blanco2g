package test.blanco.trace;

import blanco.trace.BlancoTrace;
import blanco.trace.BlancoTraceMemory;

/**
 * メモリ・トレースのテスト。
 */
public abstract class AbstractTraceMemoryTester04 {
    /**
     * メモリ・トレースのテスト。
     * 
     * @param arg01
     */
    @BlancoTraceMemory(gc = true)
    @BlancoTrace
    public void hello(final String arg01) {
        System.out.println("Hello: " + arg01);
    }
}
