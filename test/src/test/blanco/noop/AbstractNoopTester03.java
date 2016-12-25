package test.blanco.noop;

import blanco.noop.BlancoNoop;

/**
 * static メソッドに Noop を付与するテスト。
 */
public abstract class AbstractNoopTester03 {
    /**
     * static メソッドに Noop を付与するテスト。
     */
    @BlancoNoop
    public static void hello() {
        System.out.println("Hello!");
    }
}
