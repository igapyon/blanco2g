package test.blanco.cache;

import java.sql.Connection;
import java.sql.SQLException;

import blanco.cache.BlancoCache;
import blanco.cache.BlancoCacheKey;

/**
 * メソッド・キャッシュのテスト。
 */
public abstract class AbstractCacheTester21 {
    /**
     * メソッド・キャッシュのテスト。
     * 
     * @param arg01
     * @param argNoCache01
     * @return
     */
    @BlancoCache
    public String hello(@BlancoCacheKey final String arg01, final Connection argNoCache01) throws SQLException {
        return "Hello: " + arg01;
    }
}
