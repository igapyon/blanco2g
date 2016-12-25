/*******************************************************************************
 * Blanco2g
 * Copyright (C) 2012 Toshiki IGA
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
 * Copyright (c) 2012 Toshiki IGA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Toshiki IGA - initial API and implementation
 *******************************************************************************/
package blanco.validate.driver;

import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.Blanco2gValidateProcessor;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.cg.valueobject.BlancoCgReturn;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * Validate シリーズを駆動させるためのドライバー。これ自身は対応アノテーションは持ちません。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gValidateDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gValidateDriver());
    }

    //@Override
    public int getPriority() {
        return 70000;
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
    public boolean processField(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        if (generateValidateMethod(info)) {
            isModified = true;
        }

        boolean isStrutsValidateEnabled = false;
        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.validate.BlancoValidateMethodForStruts")
                    || annotationTypeName.equals("BlancoValidateMethodForStruts")) {
                isStrutsValidateEnabled = true;
            }
        }
        if (isStrutsValidateEnabled) {
            if (generateValidateMethodForStruts(info)) {
                isModified = true;
            }
        }

        boolean isJsfValidateEnabled = false;
        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.validate.BlancoValidateMethodForJsf")
                    || annotationTypeName.equals("BlancoValidateMethodForJsf")) {
                isJsfValidateEnabled = true;
            }
        }
        if (isJsfValidateEnabled) {
            if (generateValidateMethodForJsf(info)) {
                isModified = true;
            }
        }

        return isModified;
    }

    boolean generateValidateMethod(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        final BlancoCgMethod method = info.getFactory().createMethod(
                "validate" + BlancoNameAdjuster.toClassName(info.getInput().getCgField().getName()),
                info.getInput().getCgField().getDescription());

        final BlancoCgReturn cgReturn = info.getFactory().createReturn("java.lang.String", "検証結果の文字列。問題なければ null。");
        method.setReturn(cgReturn);
        method.getLangDoc().getDescriptionList().addAll(info.getInput().getCgField().getLangDoc().getDescriptionList());

        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gValidateProcessor) {
                final Blanco2gValidateProcessor processor = (Blanco2gValidateProcessor) driver;
                if (processor.processValidateField(info, method)) {
                    isModified = true;
                }
            }
        }

        method.getLineList().add("return null;");

        if (isModified)
            info.getOutput().getCgClass().getMethodList().add(method);

        return isModified;
    }

    boolean generateValidateMethodForStruts(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        final BlancoCgMethod method = info.getFactory().createMethod(
                "validate" + BlancoNameAdjuster.toClassName(info.getInput().getCgField().getName()),
                info.getInput().getCgField().getDescription());

        {
            final BlancoCgParameter cgParameter = info.getFactory().createParameter("actionMessages",
                    "org.apache.struts.action.ActionMessages", "Apache Struts ActionMessages");
            method.getParameterList().add(cgParameter);
        }

        final BlancoCgReturn cgReturn = info.getFactory().createReturn("boolean",
                "検証結果が問題無しであれば true。検証結果に問題あれば false。");
        method.setReturn(cgReturn);
        method.getLangDoc().getDescriptionList().addAll(info.getInput().getCgField().getLangDoc().getDescriptionList());
        method.getLangDoc().getDescriptionList().add("[@BlancoValidateMethodForStruts]");

        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gValidateProcessor) {
                final Blanco2gValidateProcessor processor = (Blanco2gValidateProcessor) driver;
                if (processor.canProcessStruts()) {
                    if (processor.processValidateFieldForStruts(info, method)) {
                        isModified = true;
                    }
                }
            }
        }

        method.getLineList().add("return true;");

        if (isModified)
            info.getOutput().getCgClass().getMethodList().add(method);

        return isModified;
    }

    boolean generateValidateMethodForJsf(final Blanco2gProcessingInfo info) {
        boolean isModified = false;

        final BlancoCgMethod method = info.getFactory().createMethod(
                "validate" + BlancoNameAdjuster.toClassName(info.getInput().getCgField().getName()),
                info.getInput().getCgField().getDescription());

        {
            final BlancoCgParameter cgParameter = info.getFactory().createParameter("ctx",
                    "javax.faces.context.FacesContext", "JSF Messages");
            method.getParameterList().add(cgParameter);
        }

        final BlancoCgReturn cgReturn = info.getFactory().createReturn("boolean",
                "検証結果が問題無しであれば true。検証結果に問題あれば false。");
        method.setReturn(cgReturn);
        method.getLangDoc().getDescriptionList().addAll(info.getInput().getCgField().getLangDoc().getDescriptionList());
        method.getLangDoc().getDescriptionList().add("[@BlancoValidateMethodForJsf]");

        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gValidateProcessor) {
                final Blanco2gValidateProcessor processor = (Blanco2gValidateProcessor) driver;
                if (processor.canProcessJsf()) {
                    if (processor.processValidateFieldForJsf(info, method)) {
                        isModified = true;
                    }
                }
            }
        }

        method.getLineList().add("return true;");

        if (isModified)
            info.getOutput().getCgClass().getMethodList().add(method);

        return isModified;
    }

    static boolean isSelection(final String anno) {
        final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);

        final String strIsSelection = map.get("isSelection");
        if (strIsSelection == null || strIsSelection.equals("true") == false) {
            return false;
        } else {
            return true;
        }
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }
}
