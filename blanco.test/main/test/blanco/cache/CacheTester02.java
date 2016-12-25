/*
 * このクラスは 'AbstractCacheTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester02 extends AbstractCacheTester02 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester02Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester02Hello>>(8192));

    /**
     * メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @param arg02
     * @return
     */
    public String hello(String arg01, String arg02) {
        java.lang.String autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester02Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "\t" + arg02 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester02Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
                if (autoValCacheLookup != null) {
                    if (Math.abs(System.currentTimeMillis() - autoValCacheLookup.birthMillisec) <= 86400000) {
                        // Hit cache.
                        return autoValCacheLookup.cachedValue;
                    }
                }
            }
        }
        autoValMethodResult = super.hello(arg01, arg02);
        {
            // [@BlancoCache] Remember cache.
            final CacheObjectForCacheTester02Hello autoValCacheObject = new CacheObjectForCacheTester02Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "\t" + arg02 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester02Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester02Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    String cachedValue;
}
