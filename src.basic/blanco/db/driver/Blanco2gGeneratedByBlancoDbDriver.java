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
package blanco.db.driver;

import java.util.HashMap;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gInjectProcessor;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.commons.util.BlancoNameUtil;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gGeneratedByBlancoDbDriver implements Blanco2gDriver, Blanco2gProcessor, Blanco2gInjectProcessor {
    static final boolean IS_DEBUG = true;

    final Map<String, BlancoCgClass> blancoDbMap = new HashMap<String, BlancoCgClass>();

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gGeneratedByBlancoDbDriver());
    }

    //@Override
    public int getPriority() {
        return 45000;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
        blancoDbMap.clear();
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
        blancoDbMap.clear();
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
        for (BlancoCgSourceFile cgSourceInput : info.getSourceList()) {
            for (BlancoCgClass cgClassInput : cgSourceInput.getClassList()) {
                for (String annotation : cgClassInput.getAnnotationList()) {
                    final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@"
                            + annotation);

                    if (annotationTypeName.equals("blanco.fw.BlancoGeneratedBy")
                            || annotationTypeName.equals("BlancoGeneratedBy")) {
                        final String generator = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair(
                                "@" + annotation).get("name");
                        if (generator != null && generator.equals("\"blancoDb\"")) {
                            blancoDbMap.put(cgSourceInput.getPackage() + "." + cgClassInput.getName(), cgClassInput);
                        }
                    }
                }
            }
        }
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
    public boolean canProcessMethodParameter(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        if (blancoDbMap.get(cgParameterInput.getType().getName()) != null) {
            return true;
        }
        return false;
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {
        boolean isModified = false;
        boolean isQueryOccured = false;
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput) == false)
                continue;

            if (canProcessMethodParameter(info, cgParameterInput) == false)
                continue;

            final String varDbconnection = ensureRequiredVar0(info);

            info.getOutput().getCgSource().getImportList().add(cgParameterInput.getType().getName());
            info.getOutput().getCgMethod().getLineList().add("// [@BlancoInject] Inject DAO object.");
            final String blancoDbQueryClassName = BlancoNameUtil.trimJavaPackage(cgParameterInput.getType().getName());
            info.getOutput()
                    .getCgMethod()
                    .getLineList()
                    .add("final " + blancoDbQueryClassName + " " + cgParameterInput.getName() + " = new "
                            + blancoDbQueryClassName + "(" + varDbconnection + ");");
            isQueryOccured = true;

            isModified = true;
        }

        if (isQueryOccured) {
            info.getOutput().getCgMethod().getLineList().add("try {");
        }

        return isModified;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {

        boolean isQueryOccured = false;
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput) == false)
                continue;

            if (canProcessMethodParameter(info, cgParameterInput) == false)
                continue;

            isQueryOccured = true;
        }

        if (isQueryOccured) {
            info.getOutput().getCgMethod().getLineList().add("} finally {");
        }

        boolean isModified = false;
        for (int index = info.getInput().getCgMethod().getParameterList().size() - 1; index >= 0; index--) {
            final BlancoCgParameter cgParameterInput = info.getInput().getCgMethod().getParameterList().get(index);

            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput) == false)
                continue;

            if (canProcessMethodParameter(info, cgParameterInput) == false)
                continue;

            info.getOutput().getCgMethod().getLineList().add("// [@BlancoInject] Close DAO object.");
            info.getOutput().getCgMethod().getLineList().add("try {");
            info.getOutput().getCgMethod().getLineList().add(cgParameterInput.getName() + ".close();");
            info.getOutput().getCgMethod().getLineList().add("} catch (SQLException exIgnore) {");
            info.getOutput().getCgSource().getImportList().add("java.sql.SQLException");
            info.getOutput().getCgMethod().getLineList().add("}");

            isModified = true;
        }

        if (isQueryOccured) {
            info.getOutput().getCgMethod().getLineList().add("}");
        }

        return isModified;

    }

    String getVarName(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        return cgParameterInput.getName();
    }

    static final String REQUIRED_CLASS0 = "java.sql.Connection";

    static final String REQUIRED_CLASS0_AUTOVAL_NAME = "autoValConn";

    String ensureRequiredVar0(final Blanco2gProcessingInfo info) {
        // メソッド引数や生成済みの自動変数に FacesContext が含まれていればそれを取得します。
        String varName = Blanco2gUtil.findRequiredVarFromMethodInjectCode(info, REQUIRED_CLASS0);
        if (varName != null)
            return varName;

        // 現状では該当する変数がない
        return null;
    }
}
