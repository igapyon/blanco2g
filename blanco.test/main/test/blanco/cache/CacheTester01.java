/*
 * このクラスは 'AbstractCacheTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester01 extends AbstractCacheTester01 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester01Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester01Hello>>(8192));

    /**
     * メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @return
     */
    public String hello(String arg01) {
        java.lang.String autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester01Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester01Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
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
            final CacheObjectForCacheTester01Hello autoValCacheObject = new CacheObjectForCacheTester01Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester01Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester01Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    String cachedValue;
}
