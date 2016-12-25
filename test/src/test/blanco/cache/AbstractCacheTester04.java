package test.blanco.cache;

import java.util.ArrayList;
import java.util.List;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester04 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @param arg02
     * @return
     */
    @BlancoCache
    public List<String> hello(@BlancoCacheKey final String arg01, @BlancoCacheKey final String arg02) {
        final List<String> result = new ArrayList<String>();
        result.add(arg01);
        result.add(arg02);
        return result;
    }
}
