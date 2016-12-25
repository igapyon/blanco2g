package test.blanco.noop;

import blanco.noop.BlancoNoop;

/**
 * メソッドに Noop を付与するテスト。
 */
public abstract class AbstractNoopTester02 {
    /**
     * メソッドに Noop を付与するテスト。
     */
    @BlancoNoop
    public void hello() {
        System.out.println("Hello!");
    }
}
