/*
 * このクラスは 'AbstractCacheTester04' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.cache;

import java.util.List;
import java.util.Map;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・キャッシュのテスト。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class CacheTester04 extends AbstractCacheTester04 {
    /**
     * [@BlancoCache] Cache object for method 'hello'.
     */
    private final Map<String, java.lang.ref.SoftReference<CacheObjectForCacheTester04Hello>> blanco2gCacheMethodhello = java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<CacheObjectForCacheTester04Hello>>(8192));

    /**
     * メソッド・キャッシュのテスト。
     *
     * @param arg01
     * @param arg02
     * @return
     */
    public List<java.lang.String> hello(String arg01, String arg02) {
        java.util.List<java.lang.String> autoValMethodResult;
        {
            // [@BlancoCache] Search cache.
            final java.lang.ref.SoftReference<CacheObjectForCacheTester04Hello> autoValMethodResultCacheReference = blanco2gCacheMethodhello.get("" + arg01 + "\t" + arg02 + "");
            if (autoValMethodResultCacheReference != null) {
                final CacheObjectForCacheTester04Hello autoValCacheLookup = autoValMethodResultCacheReference.get();
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
            final CacheObjectForCacheTester04Hello autoValCacheObject = new CacheObjectForCacheTester04Hello();
            autoValCacheObject.birthMillisec = System.currentTimeMillis();
            autoValCacheObject.cachedValue = autoValMethodResult;
            blanco2gCacheMethodhello.put("" + arg01 + "\t" + arg02 + "", new java.lang.ref.SoftReference<CacheObjectForCacheTester04Hello>(autoValCacheObject));
        }
        return autoValMethodResult;
    }
}
/**
 * Cache object for method 'hello'.
 */
class CacheObjectForCacheTester04Hello {
    /**
     * Birth millisec for cache object.
     */
    long birthMillisec;

    /**
     * Cached object value.
     */
    List<java.lang.String> cachedValue;
}
