package test.blanco.noop;

import blanco.noop.BlancoNoop;

/**
 * メソッドに パラメータ付き型を付与するテスト。FQCN 版。
 */
public abstract class AbstractNoopTester04 {
    /**
     * メソッドに パラメータ付き型を付与するテスト。FQCN 版。
     */
    @BlancoNoop
    public java.util.List<java.util.List<java.lang.String>> hello(java.util.List<java.util.List<java.lang.String>> arg) {
        return arg;
    }
}
