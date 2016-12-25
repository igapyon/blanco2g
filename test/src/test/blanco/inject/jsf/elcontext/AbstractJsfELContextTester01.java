package test.blanco.inject.jsf.elcontext;

import javax.el.ELContext;

import blanco.fw.BlancoInject;

/**
 * ELContext にまつわるサンプル。
 */
public abstract class AbstractJsfELContextTester01 {
    /**
     * 文字列の戻り値ありサンプル。
     * 
     * @param elc
     * @param strArg0
     * @return
     */
    public String sayHello(@BlancoInject final ELContext elc, final String strArg0) {
        return "Hello: " + strArg0;
    }
}
