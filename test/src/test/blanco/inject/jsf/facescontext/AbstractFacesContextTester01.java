package test.blanco.inject.jsf.facescontext;

import javax.faces.context.FacesContext;

import blanco.fw.BlancoInject;

/**
 * メソッド・パラメータ注入の機能: FacesContext の注入。
 */
public abstract class AbstractFacesContextTester01 {
    /**
     * メソッド・パラメータ注入の機能: FacesContext の注入。
     * 
     * @param arg01
     * @param ctx
     *            注入される FacesContext
     * @return
     */
    public String hello(final String arg01, @BlancoInject final FacesContext ctx) {
        return null;
    }
}
