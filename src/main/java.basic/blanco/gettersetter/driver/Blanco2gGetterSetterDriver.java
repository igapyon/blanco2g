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
package blanco.gettersetter.driver;

import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgReturn;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gGetterSetterDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gGetterSetterDriver());
    }

    //@Override
    public int getPriority() {
        return 15000;
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
        boolean isModified = false;

        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.gettersetter.BlancoGetterSetter")
                    || annotationTypeName.equals("BlancoGetterSetter")) {
                if ("private".equals(info.getInput().getCgField().getAccess())) {
                    info.getOutput()
                            .getCgClass()
                            .getLangDoc()
                            .getDescriptionList()
                            .add("FIXME [@BlancoGetterSetter] Field '" + info.getInput().getCgField().getName()
                                    + "' is skipped due to 'private'.");
                    isModified = true;
                    if (isDebug())
                        System.out.println("trace: Skip private field." + info.getInput().getCgClass().getName() + "#"
                                + info.getInput().getCgField().getName());
                    continue;
                }

                if (isDebug())
                    System.out.println("trace: non private filed." + info.getInput().getCgClass().getName() + "#"
                            + info.getInput().getCgField().getName());

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strIsGetter = map.get("getter");
                if (strIsGetter == null || strIsGetter.equals("false") == false) {
                    final BlancoCgMethod method = info.getFactory().createMethod(
                            "get" + BlancoNameAdjuster.toClassName(info.getInput().getCgField().getName()),
                            info.getInput().getCgField().getDescription());
                    info.getOutput().getCgClass().getMethodList().add(method);

                    final BlancoCgReturn cgReturn = info.getFactory().createReturn(
                            info.getInput().getCgField().getType().getName(), "取得したい値。");
                    cgReturn.setType(info.getInput().getCgField().getType());
                    method.setReturn(cgReturn);
                    method.setStatic(info.getInput().getCgField().getStatic());
                    method.getLangDoc().getDescriptionList()
                            .addAll(info.getInput().getCgField().getLangDoc().getDescriptionList());
                    method.getLangDoc().getDescriptionList().add("[@BlancoGetterSetter]");

                    method.getLineList().add("return " + info.getInput().getCgField().getName() + ";");
                }

                final String strIsSetter = map.get("setter");
                if (strIsSetter == null || strIsSetter.equals("false") == false) {
                    final BlancoCgMethod method = info.getFactory().createMethod(
                            "set" + BlancoNameAdjuster.toClassName(info.getInput().getCgField().getName()),
                            info.getInput().getCgField().getDescription());
                    info.getOutput().getCgClass().getMethodList().add(method);

                    method.setStatic(info.getInput().getCgField().getStatic());
                    method.getParameterList().add(
                            info.getFactory().createParameter(
                                    (method.getStatic() ? "arg" : "") + info.getInput().getCgField().getName(),
                                    info.getInput().getCgField().getType().getName()
                                            + (info.getInput().getCgField().getType().getGenerics() != null ? info
                                                    .getInput().getCgField().getType().getGenerics() : ""), "設定したい値。"));
                    method.getLangDoc().getDescriptionList()
                            .addAll(info.getInput().getCgField().getLangDoc().getDescriptionList());
                    method.getLangDoc().getDescriptionList().add("[@BlancoGetterSetter]");

                    method.getLineList().add(
                            (method.getStatic() ? "" : "this.") + info.getInput().getCgField().getName() + " = "
                                    + (method.getStatic() ? "arg" : "") + info.getInput().getCgField().getName() + ";");

                    // [addMethodCallToSetter] への対応。
                    {
                        final String strAddMethodCallToSetter = Blanco2gUtil.stripQuote(map.get("addMethodCallToSetter"));
                        if (strAddMethodCallToSetter != null && strAddMethodCallToSetter.trim().length() > 0) {
                            method.getLineList().add(
                                    (method.getStatic() ? "" : "this.") + info.getInput().getCgField().getName() + " = "
                                            + strAddMethodCallToSetter + "("
                                            + (method.getStatic() ? "" : "this.") + info.getInput().getCgField().getName() + ");");
                        }
                    }
                }

                isModified = true;
            }
        }

        return isModified;
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    static boolean isDebug() {
        return Blanco2gUtil.isDebug(Blanco2gGetterSetterDriver.class);
    }
}
