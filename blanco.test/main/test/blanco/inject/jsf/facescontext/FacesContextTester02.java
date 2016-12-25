/*
 * このクラスは 'AbstractFacesContextTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.jsf.facescontext;

import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータ注入の機能: FacesContext の注入。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class FacesContextTester02 extends AbstractFacesContextTester02 {
    /**
     * メソッド・パラメータ注入の機能: FacesContext の注入。
     *
     * @return
     */
    public String hello() {
        java.lang.String autoValMethodResult;
        final FacesContext ctx = FacesContext.getCurrentInstance();
        autoValMethodResult = super.hello(ctx);
        return autoValMethodResult;
    }
}
