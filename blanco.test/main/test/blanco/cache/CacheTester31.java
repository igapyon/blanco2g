/*
 * このクラスは 'AbstractCacheTester31' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * staic メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester31 extends AbstractCacheTester31 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private static final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester31Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester31Hello>>(8192));

    /**
     * static メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @return
     */
    public static String hello(String arg01) {
        java.lang.String autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester31Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester31Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
                if (autoValCacheLookup != null) {
                    if (Math.abs(System.currentTimeMillis() - autoValCacheLookup.birthMillisec) <= 86400000) {
                        // Hit cache.
                        return autoValCacheLookup.cachedValue;
                    }
                }
            }
        }
        autoValMethodResult = AbstractCacheTester31.hello(arg01);
        {
            // [@BlancoCache] Remember cache.
            final CacheObjectForCacheTester31Hello autoValCacheObject = new CacheObjectForCacheTester31Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester31Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester31Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    String cachedValue;
}
