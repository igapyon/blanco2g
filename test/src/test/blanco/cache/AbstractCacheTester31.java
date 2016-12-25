package test.blanco.cache;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * staic メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester31 {
    /**
     * static メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @return
     */
    @BlancoCache
    public static String hello(@BlancoCacheKey final String arg01) {
        return "Hello: " + arg01;
    }
}
