package test.blanco.cache;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester01 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @return
     */
    @BlancoCache
    public String hello(@BlancoCacheKey final String arg01) {
        return "Hello: " + arg01;
    }
}
