/*
 * このクラスは 'AbstractJsfManagedBeanTester12' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.jsf.managedbean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 * 他の管理 Bean を参照する例。
 */
@BlancoGeneratedBy(name = "Blanco2g")
@ManagedBean
@ViewScoped
public class JsfManagedBeanTester12 extends AbstractJsfManagedBeanTester12 implements Serializable {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 他の管理 Bean を参照する例。
     *
     * @param strArg0
     * @return
     */
    public String sayHello(String strArg0) {
        java.lang.String autoValMethodResult;
        final FacesContext autoValCtx = FacesContext.getCurrentInstance();
        final ELContext autoValElc = autoValCtx.getELContext();
        final ELResolver autoValRslvr = autoValCtx.getApplication().getELResolver();
        final JsfManagedBeanTester01 bean = (JsfManagedBeanTester01) autoValRslvr.getValue(autoValElc, null, "jsfManagedBeanTester01");
        autoValMethodResult = super.sayHello(bean, strArg0);
        return autoValMethodResult;
    }
}
