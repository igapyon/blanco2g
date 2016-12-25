package test.blanco.cache;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester02 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @param arg02
     * @return
     */
    @BlancoCache
    public String hello(@BlancoCacheKey final String arg01, @BlancoCacheKey final String arg02) {
        return "Hello: " + arg01 + ", " + arg02;
    }
}
