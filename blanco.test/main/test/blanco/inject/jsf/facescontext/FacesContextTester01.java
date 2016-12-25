/*
 * このクラスは 'AbstractFacesContextTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.jsf.facescontext;

import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 * メソッド・パラメータ注入の機能: FacesContext の注入。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class FacesContextTester01 extends AbstractFacesContextTester01 {
    /**
     * メソッド・パラメータ注入の機能: FacesContext の注入。
     *
     * @param arg01
     * @return
     */
    public String hello(String arg01) {
        java.lang.String autoValMethodResult;
        final FacesContext ctx = FacesContext.getCurrentInstance();
        autoValMethodResult = super.hello(arg01, ctx);
        return autoValMethodResult;
    }
}
