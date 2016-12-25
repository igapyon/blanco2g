/*
 * このクラスは 'AbstractTraceMemoryTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.trace;

import blanco.fw.BlancoGeneratedBy;

/**
 * メモリ・トレースのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class TraceMemoryTester02 extends AbstractTraceMemoryTester02 {
    /**
     * メモリ・トレースのテスト。
     *
     * @param arg01
     */
    public void hello(String arg01) {
        final long autoValMemoryUsedBeforeMethod = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + autoValMemoryUsedBeforeMethod + "/" + Runtime.getRuntime().totalMemory());
        super.hello(arg01);
        final long autoValMemoryUsedAfterMethod = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory: " + autoValMemoryUsedAfterMethod + "/" + Runtime.getRuntime().totalMemory() + " (" + (autoValMemoryUsedAfterMethod - autoValMemoryUsedBeforeMethod) + ")");
    }
}
