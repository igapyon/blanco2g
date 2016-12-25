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

import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * メモリ・トレースをはさみこむための実装。
 * 
 * TODO sysout, logger 対応の追加
 * 
 * @author Toshiki IGA
 */
public class Blanco2gTraceMemoryDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gTraceMemoryDriver());
    }

    //@Override
    public int getPriority() {
        return 5010;
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

        for (String anno : info.getInput().getCgMethod().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.trace.BlancoTraceMemory")
                    || annotationTypeName.equals("BlancoTraceMemory")) {

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strIsGetter = map.get("gc");
                if (strIsGetter != null && strIsGetter.equals("true")) {
                    info.getOutput().getCgMethod().getLineList().add("Runtime.getRuntime().gc();");
                }

                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("final long autoValMemoryUsedBeforeMethod = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();");
                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("System.out.println(\"Memory: \" + autoValMemoryUsedBeforeMethod + \"/\" + Runtime.getRuntime().totalMemory());");

                isModified = true;
            }
        }

        return isModified;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        for (String anno : info.getInput().getCgMethod().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.trace.BlancoTraceMemory")
                    || annotationTypeName.equals("BlancoTraceMemory")) {

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strIsGetter = map.get("gc");
                if (strIsGetter != null && strIsGetter.equals("true")) {
                    info.getOutput().getCgMethod().getLineList().add("Runtime.getRuntime().gc();");
                }

                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("final long autoValMemoryUsedAfterMethod = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();");
                info.getOutput()
                        .getCgMethod()
                        .getLineList()
                        .add("System.out.println(\"Memory: \" + autoValMemoryUsedAfterMethod + \"/\" + Runtime.getRuntime().totalMemory() + \" (\" + (autoValMemoryUsedAfterMethod - autoValMemoryUsedBeforeMethod) + \")\");");

                isModified = true;
            }
        }

        return isModified;
    }
}
