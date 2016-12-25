/*******************************************************************************
 * Blanco2g
 * Copyright (C) 2011 Toshiki IGA
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2011 Toshiki IGA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Toshiki IGA - initial API and implementation
 *******************************************************************************/
package blanco.jsf.driver;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gInjectProcessor;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessingMethodAutoVariable;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.commons.util.BlancoNameUtil;

public class Blanco2gJsfELResolverDriver implements Blanco2gDriver, Blanco2gProcessor, Blanco2gInjectProcessor {

    static final String TARGET_CLASS = "javax.el.ELResolver";

    static final String REQUIRED_CLASS = "javax.faces.context.FacesContext";

    static final String REQUIRED_CLASS_AUTOVAL_NAME = "autoValCtx";

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gJsfELResolverDriver());
    }

    //@Override
    public int getPriority() {
        return 30010;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void preProcess(Blanco2gProcessingInfo info) {
    }

    //@Override
    public boolean processClass(Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean canProcessMethodParameter(Blanco2gProcessingInfo info, BlancoCgParameter cgParameterInput) {
        if (TARGET_CLASS.equals(cgParameterInput.getType().getName())) {
            return true;
        }
        return false;
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {

        boolean isModified = false;
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput) == false)
                continue;

            if (canProcessMethodParameter(info, cgParameterInput) == false)
                continue;

            final String reqVarName = ensureRequiredVar(info);

            buildAutoVariable(info, getVarName(info, cgParameterInput), reqVarName);

            isModified = true;
        }
        return isModified;
    }

    /**
     * 自動変数を構築します。
     * 
     * 他クラスから利用されます。
     * 
     * @param info
     * @param reqVarName
     */
    void buildAutoVariable(final Blanco2gProcessingInfo info, final String varName, final String reqVarName) {
        info.getOutput().getCgSource().getImportList().add(TARGET_CLASS);
        info.getOutput()
                .getCgMethod()
                .getLineList()
                .add("final " + BlancoNameUtil.trimJavaPackage(TARGET_CLASS) + " " + varName + " = " + reqVarName
                        + ".getApplication().getELResolver();");
    }

    String ensureRequiredVar(final Blanco2gProcessingInfo info) {
        // メソッド引数や生成済みの自動変数に FacesContext が含まれていればそれを取得します。
        String varName = Blanco2gUtil.findRequiredVarFromMethodInjectCode(info, REQUIRED_CLASS);
        if (varName != null)
            return varName;

        // 現状では該当する変数がないので自前で作ります。
        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gJsfFacesContextDriver) {
                final Blanco2gJsfFacesContextDriver ctxDriver = (Blanco2gJsfFacesContextDriver) driver;
                ctxDriver.buildAutoVariable(info, REQUIRED_CLASS_AUTOVAL_NAME);
                final Blanco2gProcessingMethodAutoVariable autoVar = new Blanco2gProcessingMethodAutoVariable();
                autoVar.varName = REQUIRED_CLASS_AUTOVAL_NAME;
                autoVar.classTypeName = REQUIRED_CLASS;
                info.methodAutoVariable.add(autoVar);

                return autoVar.varName;
            }
        }
        return null;
    }

    //@Override
    public boolean postCallMethod(Blanco2gProcessingInfo info) {
        return false;
    }

    String getVarName(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        return cgParameterInput.getName();
    }
}
