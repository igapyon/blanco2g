/*
 * このクラスは 'AbstractCacheTester11' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester11 extends AbstractCacheTester11 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester11Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester11Hello>>(8192));

    /**
     * メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @return
     */
    public String hello(int arg01) {
        java.lang.String autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester11Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester11Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
                if (autoValCacheLookup != null) {
                    if (Math.abs(System.currentTimeMillis() - autoValCacheLookup.birthMillisec) <= 86400000) {
                        // Hit cache.
                        return autoValCacheLookup.cachedValue;
                    }
                }
            }
        }
        autoValMethodResult = super.hello(arg01);
        {
            // [@BlancoCache] Remember cache.
            final CacheObjectForCacheTester11Hello autoValCacheObject = new CacheObjectForCacheTester11Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester11Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester11Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    String cachedValue;
}
