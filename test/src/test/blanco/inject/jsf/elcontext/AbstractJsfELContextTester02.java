package test.blanco.inject.jsf.elcontext;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import blanco.fw.BlancoInject;

public abstract class AbstractJsfELContextTester02 {
    public void sayHello(@BlancoInject final FacesContext ctx0, @BlancoInject final ELContext elc0, final String strArg0) {
        System.out.println("Hello: " + strArg0);
    }
}
