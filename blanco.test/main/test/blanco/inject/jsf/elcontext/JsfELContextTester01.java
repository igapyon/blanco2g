/*
 * このクラスは 'AbstractJsfELContextTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.jsf.elcontext;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 * ELContext にまつわるサンプル。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class JsfELContextTester01 extends AbstractJsfELContextTester01 {
    /**
     * 文字列の戻り値ありサンプル。
     *
     * @param strArg0
     * @return
     */
    public String sayHello(String strArg0) {
        java.lang.String autoValMethodResult;
        final FacesContext autoValCtx = FacesContext.getCurrentInstance();
        final ELContext elc = autoValCtx.getELContext();
        autoValMethodResult = super.sayHello(elc, strArg0);
        return autoValMethodResult;
    }
}
