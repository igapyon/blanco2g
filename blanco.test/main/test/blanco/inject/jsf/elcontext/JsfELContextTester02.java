/*
 * このクラスは 'AbstractJsfELContextTester02' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.jsf.elcontext;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class JsfELContextTester02 extends AbstractJsfELContextTester02 {
    /**
     *
     * @param strArg0
     */
    public void sayHello(String strArg0) {
        final FacesContext ctx0 = FacesContext.getCurrentInstance();
        final ELContext elc0 = ctx0.getELContext();
        super.sayHello(ctx0, elc0, strArg0);
    }
}
