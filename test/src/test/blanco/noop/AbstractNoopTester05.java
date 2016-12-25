package test.blanco.noop;

import java.util.List;

import blanco.noop.BlancoNoop;

/**
 * メソッドに パラメータ付き型を付与するテスト。
 */
public abstract class AbstractNoopTester05 {
    /**
     * メソッドに パラメータ付き型を付与するテスト。
     */
    @BlancoNoop
    public List<List<String>> hello(List<List<String>> arg) {
        return arg;
    }
}
