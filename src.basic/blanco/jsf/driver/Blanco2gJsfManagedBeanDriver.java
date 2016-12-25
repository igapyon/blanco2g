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

import java.util.HashMap;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gInjectProcessor;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessingMethodAutoVariable;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.commons.util.BlancoNameUtil;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * Driver for JavaServer Facelets ManagedBean
 * 
 * @author Toshiki IGA
 */
public class Blanco2gJsfManagedBeanDriver implements Blanco2gDriver, Blanco2gProcessor, Blanco2gInjectProcessor {
    static final boolean IS_DEBUG = true;

    final Map<String, BlancoCgClass> managedBeanMap = new HashMap<String, BlancoCgClass>();

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gJsfManagedBeanDriver());
    }

    //@Override
    public int getPriority() {
        return 35000;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
        managedBeanMap.clear();
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
        managedBeanMap.clear();
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
        for (BlancoCgSourceFile cgSourceInput : info.getSourceList()) {
            for (BlancoCgClass cgClassInput : cgSourceInput.getClassList()) {
                for (String annotation : cgClassInput.getAnnotationList()) {
                    final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@"
                            + annotation);
                    if (annotationTypeName.equals("javax.faces.bean.ManagedBean")
                            || annotationTypeName.equals("ManagedBean")) {
                        managedBeanMap.put(cgSourceInput.getPackage() + "." + cgClassInput.getName(), cgClassInput);
                    }
                }
            }
        }
    }

    //@Override
    public boolean processClass(Blanco2gProcessingInfo info) {
        boolean isModified = false;
        for (String annotation : info.getInput().getCgClass().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + annotation);
            if ("blanco.jsf.BlancoJsfManagedBean".equals(annotationTypeName)
                    || "BlancoJsfManagedBean".equals(annotationTypeName)) {
                // ソースコードが変更されました。
                isModified = true;

                info.getOutput().getCgClass().getImplementInterfaceList()
                        .add(info.getFactory().createType("java.io.Serializable"));
                {
                    // Constants for Serializable
                    final BlancoCgField cgField = info.getFactory().createField("serialVersionUID", "long",
                            "シリアルバージョン UID.");
                    info.getOutput().getCgClass().getFieldList().add(cgField);
                    cgField.setDefault("1L");
                    cgField.setStatic(true);
                    cgField.setFinal(true);
                }

                info.getOutput().getCgSource().getImportList().add("javax.faces.bean.ManagedBean");
                info.getOutput().getCgClass().getAnnotationList().add("ManagedBean");

                {
                    // スコープの確定
                    final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@"
                            + annotation);
                    String scopeName = map.get("scope");
                    if (scopeName != null) {
                        if (scopeName.startsWith("\"")) {
                            scopeName = scopeName.substring(1);
                        }
                        if (scopeName.endsWith("\"")) {
                            scopeName = scopeName.substring(0, scopeName.length() - 1);
                        }
                    } else {
                        // デフォルトは ViewScoped です。
                        scopeName = "view";
                    }

                    scopeName = BlancoNameAdjuster.toClassName(scopeName);

                    info.getOutput().getCgSource().getImportList().add("javax.faces.bean." + scopeName + "Scoped");
                    info.getOutput().getCgClass().getAnnotationList().add(scopeName + "Scoped");
                }
            }
        }

        return isModified;
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean canProcessMethodParameter(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        if (managedBeanMap.get(cgParameterInput.getType().getName()) != null) {
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

            final String varFacesContext = ensureRequiredVar0(info);
            final String var1 = ensureRequiredVar1(info, varFacesContext);
            final String var2 = ensureRequiredVar2(info, varFacesContext);

            info.getOutput().getCgSource().getImportList().add(cgParameterInput.getType().getName());
            final String managedBeanClassName = BlancoNameUtil.trimJavaPackage(cgParameterInput.getType().getName());
            final String managedBeanName = BlancoNameAdjuster.toLowerCaseTitle(managedBeanClassName);
            info.getOutput()
                    .getCgMethod()
                    .getLineList()
                    .add("final " + managedBeanClassName + " " + cgParameterInput.getName() + " = ("
                            + managedBeanClassName + ") " + var2 + ".getValue(" + var1 + ", null, \"" + managedBeanName
                            + "\");");

            isModified = true;
        }
        return isModified;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    String getVarName(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        return cgParameterInput.getName();
    }

    static final String REQUIRED_CLASS0 = "javax.faces.context.FacesContext";

    static final String REQUIRED_CLASS0_AUTOVAL_NAME = "autoValCtx";

    static final String REQUIRED_CLASS1 = "javax.el.ELContext";

    static final String REQUIRED_CLASS1_AUTOVAL_NAME = "autoValElc";

    static final String REQUIRED_CLASS2 = "javax.el.ELResolver";

    static final String REQUIRED_CLASS2_AUTOVAL_NAME = "autoValRslvr";

    String ensureRequiredVar0(final Blanco2gProcessingInfo info) {
        // メソッド引数や生成済みの自動変数に FacesContext が含まれていればそれを取得します。
        String varName = Blanco2gUtil.findRequiredVarFromMethodInjectCode(info, REQUIRED_CLASS0);
        if (varName != null)
            return varName;

        // 現状では該当する変数がないので自前で作ります。
        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gJsfFacesContextDriver) {
                final Blanco2gJsfFacesContextDriver lookupDriver = (Blanco2gJsfFacesContextDriver) driver;
                lookupDriver.buildAutoVariable(info, REQUIRED_CLASS0_AUTOVAL_NAME);
                final Blanco2gProcessingMethodAutoVariable autoVar = new Blanco2gProcessingMethodAutoVariable();
                autoVar.varName = REQUIRED_CLASS0_AUTOVAL_NAME;
                autoVar.classTypeName = REQUIRED_CLASS0;
                info.methodAutoVariable.add(autoVar);

                return autoVar.varName;
            }
        }
        return null;
    }

    String ensureRequiredVar1(final Blanco2gProcessingInfo info, final String varFacesContext) {
        String varName = Blanco2gUtil.findRequiredVarFromMethodInjectCode(info, REQUIRED_CLASS1);
        if (varName != null)
            return varName;

        // 現状では該当する変数がないので自前で作ります。
        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gJsfELContextDriver) {
                final Blanco2gJsfELContextDriver lookupDriver = (Blanco2gJsfELContextDriver) driver;
                lookupDriver.buildAutoVariable(info, REQUIRED_CLASS1_AUTOVAL_NAME, varFacesContext);
                final Blanco2gProcessingMethodAutoVariable autoVar = new Blanco2gProcessingMethodAutoVariable();
                autoVar.varName = REQUIRED_CLASS1_AUTOVAL_NAME;
                autoVar.classTypeName = REQUIRED_CLASS1;
                info.methodAutoVariable.add(autoVar);

                return autoVar.varName;
            }
        }
        return null;
    }

    String ensureRequiredVar2(final Blanco2gProcessingInfo info, final String varFacesContext) {
        // メソッド引数や生成済みの自動変数に FacesContext が含まれていればそれを取得します。
        String varName = Blanco2gUtil.findRequiredVarFromMethodInjectCode(info, REQUIRED_CLASS2);
        if (varName != null)
            return varName;

        // 現状では該当する変数がないので自前で作ります。
        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gJsfELResolverDriver) {
                final Blanco2gJsfELResolverDriver lookupDriver = (Blanco2gJsfELResolverDriver) driver;
                lookupDriver.buildAutoVariable(info, REQUIRED_CLASS2_AUTOVAL_NAME, varFacesContext);
                final Blanco2gProcessingMethodAutoVariable autoVar = new Blanco2gProcessingMethodAutoVariable();
                autoVar.varName = REQUIRED_CLASS2_AUTOVAL_NAME;
                autoVar.classTypeName = REQUIRED_CLASS2;
                info.methodAutoVariable.add(autoVar);

                return autoVar.varName;
            }
        }
        return null;
    }
}
