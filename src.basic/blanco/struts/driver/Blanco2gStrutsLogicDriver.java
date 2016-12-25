/*******************************************************************************
 * Blanco2g
 * Copyright (C) 2011 NTT DATA Business Brains
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
 * Copyright (c) 2011 NTT DATA Business Brains and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      NTT DATA Business Brains - initial API and implementation
 *******************************************************************************/
package blanco.struts.driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.BlancoCgTransformer;
import blanco.cg.transformer.BlancoCgTransformerFactory;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.cg.valueobject.BlancoCgReturn;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.commons.util.BlancoFileUtil;
import blanco.commons.util.BlancoJavaSourceUtil;
import blanco.commons.util.BlancoNameUtil;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gStrutsLogicDriver implements Blanco2gDriver, Blanco2gProcessor {
    static final boolean IS_DEBUG = true;

    final List<StrutsActionInfo> strutsActionInfoList = new ArrayList<StrutsActionInfo>();

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gStrutsLogicDriver());
    }

    //@Override
    public int getPriority() {
        return 36010;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
        strutsActionInfoList.clear();
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
        if (strutsActionInfoList.size() > 0) {
            // 登録がある場合にのみ XML ファイルを生成します。

            // ソートします。
            Collections.sort(strutsActionInfoList, new Comparator<StrutsActionInfo>() {
                //@Override
                public int compare(StrutsActionInfo arg0, StrutsActionInfo arg1) {
                    return arg0.type.compareTo(arg1.type);
                }
            });

            new File(info.getTargetDir() + "/struts").mkdirs();

            final StringBuilder strutsConfigBody = new StringBuilder();
            strutsConfigBody.append("  <action-mappings>\n");
            for (StrutsActionInfo actionInfo : strutsActionInfoList) {
                if (actionInfo.description != null) {
                    strutsConfigBody.append("    <!-- "
                            + BlancoJavaSourceUtil.escapeStringAsJavaDoc(actionInfo.description) + " -->\n");
                }

                strutsConfigBody.append("    <action type=\"" + actionInfo.type + "\" name=\"" + actionInfo.name
                        + "\" path=\"" + actionInfo.path + "\"");
                if (actionInfo.scope != null && actionInfo.scope.length() > 0) {
                    strutsConfigBody.append(" scope=\"" + actionInfo.scope + "\"");
                }
                strutsConfigBody.append(">\n");
                for (StrutsForwardInfo forwardInfo : actionInfo.forwardList) {
                    if (forwardInfo.description != null) {
                        strutsConfigBody.append("      <!-- "
                                + BlancoJavaSourceUtil.escapeStringAsJavaDoc(forwardInfo.description) + " -->\n");
                    }
                    strutsConfigBody.append("      <forward name=\"" + forwardInfo.name + "\" path=\""
                            + forwardInfo.path + "\"" + (forwardInfo.redirect ? " redirect=\"true\"" : "") + "/>\n");
                }
                strutsConfigBody.append("    </action>\n\n");
            }
            strutsConfigBody.append("  </action-mappings>");

            try {
                BlancoFileUtil.bytes2File(Blanco2gStrutsConfigUtil.getStrutsConfigXml(strutsConfigBody.toString()),
                        new File(info.getTargetDir() + "/struts/struts-config-action-blanco.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        strutsActionInfoList.clear();
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public boolean processClass(final Blanco2gProcessingInfo info) {
        boolean isModified = false;
        for (String anno : info.getInput().getCgClass().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.struts.BlancoStrutsLogic")
                    || annotationTypeName.equals("BlancoStrutsLogic")) {

                // Struts ロジックについては、強制的に生成フラグを ON にします。
                isModified = true;

                if (info.getInput().getCgClass().getName().endsWith("Logic") == false) {
                    info.getOutput().getCgClass().getLangDoc().getDescriptionList()
                            .add("@BlancoStrutsLogic: Logic class name must ends with 'Logic'");
                }

                // ロジッククラスとは別に Action クラスを生成します。
                final BlancoCgSourceFile cgSourceAction = info.getFactory().createSourceFile(
                        info.getInput().getCgSource().getPackage(), info.getInput().getCgSource().getDescription());
                info.getSourceList().add(cgSourceAction);
                // FIXME
                cgSourceAction.setEncoding("UTF-8");

                final String logicClassName = info.getInput().getCgClass().getName().substring("Abstract".length());
                final String baseClassName = logicClassName.substring(0, logicClassName.length() - "Logic".length());
                final BlancoCgClass cgClassAction = info.getFactory().createClass(baseClassName + "Action",
                        info.getInput().getCgClass().getDescription());
                cgSourceAction.getClassList().add(cgClassAction);

                // JavaDoc を転送。
                cgClassAction.getLangDoc().getDescriptionList()
                        .addAll(info.getInput().getCgClass().getLangDoc().getDescriptionList());

                cgClassAction.getExtendClassList().add(info.getFactory().createType("org.apache.struts.action.Action"));

                cgSourceAction.getImportList().add("blanco.fw.BlancoGeneratedBy");
                cgClassAction.getAnnotationList().add("BlancoGeneratedBy(name = \"Blanco2g\")");

                final BlancoCgMethod cgMethodAction = info.getFactory().createMethod("execute", "アクション");
                cgClassAction.getMethodList().add(cgMethodAction);

                cgMethodAction.getThrowList().add(info.getFactory().createException("java.lang.Exception", "例外"));
                {
                    final BlancoCgParameter cgParameter = info.getFactory().createParameter("mapping",
                            "org.apache.struts.action.ActionMapping", "action mapping.");
                    cgMethodAction.getParameterList().add(cgParameter);
                }
                {
                    final BlancoCgParameter cgParameter = info.getFactory().createParameter("form",
                            "org.apache.struts.action.ActionForm", "action form.");
                    cgMethodAction.getParameterList().add(cgParameter);
                }
                {
                    final BlancoCgParameter cgParameter = info.getFactory().createParameter("request",
                            "javax.servlet.http.HttpServletRequest", "request.");
                    cgMethodAction.getParameterList().add(cgParameter);
                }
                {
                    final BlancoCgParameter cgParameter = info.getFactory().createParameter("response",
                            "javax.servlet.http.HttpServletResponse", "response.");
                    cgMethodAction.getParameterList().add(cgParameter);
                }

                {
                    final BlancoCgReturn cgReturn = info.getFactory().createReturn(
                            "org.apache.struts.action.ActionForward", "戻り値。");
                    cgMethodAction.setReturn(cgReturn);
                }
                cgMethodAction.getAnnotationList().add("Override");

                // フォーム名は execute メソッドの第二引数から導出します。
                String form = null;
                {
                    for (BlancoCgMethod cgMethod : info.getInput().getCgClass().getMethodList()) {
                        if (cgMethod.getName().equals("execute")) {
                            if (cgMethod.getStatic()) {
                                continue;
                            }

                            int argIndex = 0;
                            for (BlancoCgParameter cgParam : cgMethod.getParameterList()) {
                                // FIXME BlancoInject のフィールドはスキップすること。

                                switch (argIndex) {
                                case 0:
                                    if ("org.apache.struts.action.ActionMapping".equals(cgParam.getType().getName()) == false) {
                                        info.getOutput()
                                                .getCgClass()
                                                .getLangDoc()
                                                .getDescriptionList()
                                                .add("FIXME @BlancoStrutsLogic: 1st argument of method 'execute' must be 'org.apache.struts.action.ActionMapping'.");
                                    }
                                    break;
                                case 1:
                                    form = cgParam.getType().getName();
                                    break;
                                case 2:
                                    if ("javax.servlet.http.HttpServletRequest".equals(cgParam.getType().getName()) == false) {
                                        info.getOutput()
                                                .getCgClass()
                                                .getLangDoc()
                                                .getDescriptionList()
                                                .add("FIXME @BlancoStrutsLogic: 3rd argument of method 'execute' must be 'javax.servlet.http.HttpServletRequest'.");
                                    }
                                    break;
                                case 3:
                                    if ("javax.servlet.http.HttpServletResponse".equals(cgParam.getType().getName()) == false) {
                                        info.getOutput()
                                                .getCgClass()
                                                .getLangDoc()
                                                .getDescriptionList()
                                                .add("FIXME @BlancoStrutsLogic: 4th argument of method 'execute' must be 'javax.servlet.http.HttpServletResponse'.");
                                    }
                                    break;
                                }
                                argIndex++;
                            }
                        }
                    }
                }
                if (form == null) {
                    info.getOutput().getCgClass().getLangDoc().getDescriptionList()
                            .add("FIXME @BlancoStrutsLogic: execute method not found.");
                    continue;
                }

                String formClassName = form;

                cgMethodAction.getLineList().add("final " + logicClassName + " logic = new " + logicClassName + "();");
                cgMethodAction.getLineList().add(
                        "return mapping.findForward(logic.execute(mapping, (" + formClassName
                                + ") form, request, response));");

                {
                    final StrutsActionInfo logic = new StrutsActionInfo();
                    // フォーム名は引数から導出する
                    logic.type = info.getOutput().getCgSource().getPackage() + "." + baseClassName + "Action";
                    logic.name = BlancoNameUtil.trimJavaPackage(formClassName);

                    {
                        final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@"
                                + anno);
                        logic.path = Blanco2gUtil.stripQuote(map.get("path"));
                        logic.scope = Blanco2gUtil.stripQuote(map.get("scope"));
                    }

                    logic.description = info.getInput().getCgClass().getDescription();
                    if (logic.description == null
                            && info.getInput().getCgClass().getLangDoc().getDescriptionList().size() > 0) {
                        logic.description = info.getInput().getCgClass().getLangDoc().getDescriptionList().get(0);
                    }

                    strutsActionInfoList.add(logic);
                }

                final BlancoCgTransformer transformer = BlancoCgTransformerFactory.getJavaSourceTransformer();
                transformer.transform(cgSourceAction, new File(info.getTargetDir()));
            }
        }

        return isModified;
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.struts.BlancoStrutsForward")
                    || annotationTypeName.equals("BlancoStrutsForward")) {
                if (info.getInput().getCgField().getFinal() == false) {
                    info.getOutput()
                            .getCgClass()
                            .getLangDoc()
                            .getDescriptionList()
                            .add("FIXME @BlancoStrutsForward: field '" + info.getInput().getCgField().getName()
                                    + "' must be 'final'.");
                }
                if ("java.lang.String".equals(info.getInput().getCgField().getType().getName()) == false) {
                    info.getOutput()
                            .getCgClass()
                            .getLangDoc()
                            .getDescriptionList()
                            .add("FIXME @BlancoStrutsForward: field '" + info.getInput().getCgField().getName()
                                    + "' must be 'java.lang.String'.");
                }

                if (strutsActionInfoList.size() == 0) {
                    info.getOutput().getCgClass().getLangDoc().getDescriptionList()
                            .add("FIXME @BlancoStrutsForward: no execute method found.");
                    continue;
                }

                final StrutsForwardInfo forwardInfo = new StrutsForwardInfo();
                strutsActionInfoList.get(strutsActionInfoList.size() - 1).forwardList.add(forwardInfo);
                {
                    final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@"
                            + anno);
                    forwardInfo.name = Blanco2gUtil.stripQuote(info.getInput().getCgField().getDefault());
                    forwardInfo.path = Blanco2gUtil.stripQuote(map.get("path"));

                    final String redirect = map.get("redirect");
                    if ("true".equals(redirect))
                        forwardInfo.redirect = true;
                }

                forwardInfo.description = info.getInput().getCgField().getDescription();
                if (forwardInfo.description == null
                        && info.getInput().getCgField().getLangDoc().getDescriptionList().size() > 0) {
                    forwardInfo.description = info.getInput().getCgField().getLangDoc().getDescriptionList().get(0);
                }
            }
        }

        return false;
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

class StrutsActionInfo {
    String type = null;
    String name = null;
    String path = null;
    String scope = "";
    String description = null;
    List<StrutsForwardInfo> forwardList = new ArrayList<StrutsForwardInfo>();
}

class StrutsForwardInfo {
    String name = null;
    String path = null;
    boolean redirect = false;
    String description = null;
}
