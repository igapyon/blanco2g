package test.blanco.cache;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester13 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @return
     */
    @BlancoCache
    public String hello(@BlancoCacheKey final char arg01) {
        return "Hello: " + String.valueOf(arg01);
    }
}
