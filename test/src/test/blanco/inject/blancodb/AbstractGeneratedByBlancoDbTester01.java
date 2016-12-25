package test.blanco.inject.blancodb;

import java.sql.Connection;

import blanco.fw.BlancoInject;

/**
 * blancoDb が生成したオブジェクトの注入の試験。
 */
public abstract class AbstractGeneratedByBlancoDbTester01 {
    /**
     * blancoDb が生成したオブジェクトの注入の試験。
     * 
     * @param conn0
     *            注入されるデータベース接続。
     * @param iterator
     *            SQL オブジェクト。
     */
    public void a01(@BlancoInject final Connection conn0,
            @BlancoInject final SampleBlancoDbIterator iterator) {
    }
}
