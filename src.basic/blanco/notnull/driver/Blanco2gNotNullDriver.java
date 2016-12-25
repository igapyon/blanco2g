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
package blanco.notnull.driver;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.commons.util.BlancoNameUtil;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * 非 NULL を表現するためのアノテーション。
 * 
 * 初期バージョンはパラメータのみ対応。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gNotNullDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gNotNullDriver());
    }

    //@Override
    public int getPriority() {
        return 50000;
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
    public boolean processClass(final Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (isMyParameter(cgParameterInput) == false)
                continue;

            info.getOutput().getCgMethod().getLineList().add("if (" + cgParameterInput.getName() + " == null) {");
            info.getOutput()
                    .getCgMethod()
                    .getLineList()
                    .add("throw new IllegalArgumentException(\"不正引数例外: " + info.getOutput().getCgClass().getName()
                            + "#" + info.getOutput().getCgMethod().getName() + "("
                            + getParameterString(info.getInput().getCgMethod()) + ") のパラメータ '"
                            + cgParameterInput.getName() + "' に null を与えることはできません。\");");
            info.getOutput().getCgMethod().getLineList().add("}");

            isModified = true;
        }

        return isModified;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    private static boolean isMyParameter(final BlancoCgParameter cgParameterInput) {
        boolean isMyParameter = false;
        for (String annoParam : cgParameterInput.getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + annoParam);
            if (annotationTypeName.equals("blanco.notnull.BlancoNotNull") || annotationTypeName.equals("BlancoNotNull")) {
                isMyParameter = true;
            }
        }
        return isMyParameter;
    }

    String getParameterString(BlancoCgMethod cgMethodInput) {
        final StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (BlancoCgParameter cgParameterInput : cgMethodInput.getParameterList()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append(", ");
            }
            builder.append(BlancoNameUtil.trimJavaPackage(cgParameterInput.getType().getName()));
            builder.append(" ");
            builder.append(cgParameterInput.getName());
        }
        return builder.toString();
    }
}
