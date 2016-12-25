package test.blanco.cache;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester03 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @param arg02
     * @return
     */
    @BlancoCache
    public Integer hello(@BlancoCacheKey final String arg01, @BlancoCacheKey final String arg02) {
        return new Integer(123);
    }
}
