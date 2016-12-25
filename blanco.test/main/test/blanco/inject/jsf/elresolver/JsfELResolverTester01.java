/*
 * このクラスは 'AbstractJsfELResolverTester01' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.inject.jsf.elresolver;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class JsfELResolverTester01 extends AbstractJsfELResolverTester01 {
    /**
     */
    public void a01() {
        final FacesContext autoValCtx = FacesContext.getCurrentInstance();
        final ELResolver rslvr0 = autoValCtx.getApplication().getELResolver();
        super.a01(rslvr0);
    }
}
