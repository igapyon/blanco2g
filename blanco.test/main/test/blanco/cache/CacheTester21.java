/*
 * このクラスは 'AbstractCacheTester21' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester21 extends AbstractCacheTester21 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester21Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester21Hello>>(8192));

    /**
     * メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @param argNoCache01
     * @return
     * @throws SQLException
     */
    public String hello(String arg01, Connection argNoCache01) throws SQLException {
        java.lang.String autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester21Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester21Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
                if (autoValCacheLookup != null) {
                    if (Math.abs(System.currentTimeMillis() - autoValCacheLookup.birthMillisec) <= 86400000) {
                        // Hit cache.
                        return autoValCacheLookup.cachedValue;
                    }
                }
            }
        }
        autoValMethodResult = super.hello(arg01, argNoCache01);
        {
            // [@BlancoCache] Remember cache.
            final CacheObjectForCacheTester21Hello autoValCacheObject = new CacheObjectForCacheTester21Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester21Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester21Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    String cachedValue;
}
