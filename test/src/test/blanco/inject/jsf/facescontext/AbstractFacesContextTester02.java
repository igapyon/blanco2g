package test.blanco.inject.jsf.facescontext;

import javax.faces.context.FacesContext;

import blanco.fw.BlancoInject;

/**
 * メソッド・パラメータ注入の機能: FacesContext の注入。
 */
public abstract class AbstractFacesContextTester02 {
    /**
     * メソッド・パラメータ注入の機能: FacesContext の注入。
     * 
     * @param ctx
     *            注入される FacesContext
     * @return
     */
    public String hello(@BlancoInject final FacesContext ctx) {
        return null;
    }
}
