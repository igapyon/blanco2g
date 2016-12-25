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
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.commons.util.BlancoNameUtil;

public class Blanco2gJsfFacesContextDriver implements Blanco2gDriver, Blanco2gProcessor, Blanco2gInjectProcessor {
    static final boolean IS_DEBUG = true;

    static final String TARGET_CLASS = "javax.faces.context.FacesContext";

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gJsfFacesContextDriver());
    }

    //@Override
    public int getPriority() {
        return 20000;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
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

            buildAutoVariable(info, getVarName(info, cgParameterInput));

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
     * @param varName
     */
    void buildAutoVariable(final Blanco2gProcessingInfo info, final String varName) {
        info.getOutput().getCgSource().getImportList().add(TARGET_CLASS);
        info.getOutput()
                .getCgMethod()
                .getLineList()
                .add("final " + BlancoNameUtil.trimJavaPackage(TARGET_CLASS) + " " + varName + " = "
                        + BlancoNameUtil.trimJavaPackage(TARGET_CLASS) + ".getCurrentInstance();");
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    String getVarName(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        return cgParameterInput.getName();
    }
}
