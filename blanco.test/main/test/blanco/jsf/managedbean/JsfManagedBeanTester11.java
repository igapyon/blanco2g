/*
 * このクラスは 'AbstractJsfManagedBeanTester11' の具象クラスとして blanco Framework によって自動生成されました。
 */
package test.blanco.jsf.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoGeneratedBy;

/**
 * 管理 Bean と FacesContext との組み合わせ。
 */
@BlancoGeneratedBy(name = "Blanco2g")
@ManagedBean
@ViewScoped
public class JsfManagedBeanTester11 extends AbstractJsfManagedBeanTester11 implements Serializable {
    /**
     * シリアルバージョン UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 管理 Bean と FacesContext との組み合わせ。
     */
    public void sayHello() {
        final FacesContext ctx0 = FacesContext.getCurrentInstance();
        super.sayHello(ctx0);
    }
}
