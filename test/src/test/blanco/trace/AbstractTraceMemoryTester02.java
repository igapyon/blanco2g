package test.blanco.trace;

import blanco.trace.BlancoTraceMemory;

/**
 * メモリ・トレースのテスト。
 */
public abstract class AbstractTraceMemoryTester02 {
    /**
     * メモリ・トレースのテスト。
     * 
     * @param arg01
     */
    @BlancoTraceMemory(gc = false)
    public void hello(final String arg01) {
        System.out.println("Hello: " + arg01);
    }
}
