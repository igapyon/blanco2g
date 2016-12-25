package test.blanco.jsf.managedbean;

import javax.faces.context.FacesContext;

import blanco.fw.BlancoInject;
import blanco.jsf.BlancoJsfManagedBean;

/**
 * 管理 Bean と FacesContext との組み合わせ。
 */
@BlancoJsfManagedBean
public abstract class AbstractJsfManagedBeanTester11 {
    /**
     * 管理 Bean と FacesContext との組み合わせ。
     * 
     * @param ctx0
     */
    public void sayHello(@BlancoInject final FacesContext ctx0) {
    }
}
