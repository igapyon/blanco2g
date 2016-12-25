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
package blanco.trace.driver;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * トレースをはさみこむための実装。
 * 
 * TODO sysout, logger 対応の追加
 * 
 * @author Toshiki IGA
 */
public class Blanco2gTraceDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gTraceDriver());
    }

    //@Override
    public int getPriority() {
        return 5000;
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

        // TODO 引数のトレースへの展開。

        for (String anno : info.getInput().getCgMethod().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.trace.BlancoTrace") || annotationTypeName.equals("BlancoTrace")) {
                info.getOutput().getCgMethod().getLineList()
                        .add("long autoValStartTimeMillisec = System.currentTimeMillis();");
                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("System.out.println(\"trace: " + info.getInput().getCgClass().getName() + "#"
                                + info.getInput().getCgMethod().getName() + "(\"" + getMethodParameterAsString(info)
                                + "\") begin.\");");

                isModified = true;
            }
        }

        return isModified;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        final boolean hasReturnValue = (info.getOutput().getCgMethod().getReturn() != null && "void".equals(info
                .getOutput().getCgMethod().getReturn().getType().getName()) == false);

        for (String anno : info.getInput().getCgMethod().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.trace.BlancoTrace") || annotationTypeName.equals("BlancoTrace")) {
                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("System.out.println(\"trace: " + info.getInput().getCgClass().getName() + "#"
                                + info.getInput().getCgMethod().getName() + "(\"" + getMethodParameterAsString(info)
                                + "\") end. \"" + (hasReturnValue ? " + \"[\" + autoValMethodResult + \"] \"" : "")
                                + " + (System.currentTimeMillis() - autoValStartTimeMillisec) + \"ms\");");

                isModified = true;
            }
        }

        return isModified;
    }

    String getMethodParameterAsString(final Blanco2gProcessingInfo info) {
        final StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput))
                continue;

            if (isFirst) {
                isFirst = false;
                builder.append(" + ");
            } else {
                builder.append("\", \" + ");
            }

            builder.append(cgParameterInput.getName());
            builder.append(" + ");
        }

        if (isFirst) {
            builder.append(" + ");
        }

        return builder.toString();
    }
}
