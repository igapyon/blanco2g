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

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.commons.util.BlancoFileUtil;
import blanco.commons.util.BlancoJavaSourceUtil;
import blanco.commons.util.BlancoNameUtil;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * Struts の Form を処理するための Blanco2g ドライバー。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gStrutsFormDriver implements Blanco2gDriver, Blanco2gProcessor {
    static final boolean IS_DEBUG = true;

    final List<StrutsFormInfo> strutsFormInfoList = new ArrayList<StrutsFormInfo>();

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gStrutsFormDriver());
    }

    //@Override
    public int getPriority() {
        return 36000;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
        strutsFormInfoList.clear();
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
        if (strutsFormInfoList.size() > 0) {
            // 登録がある場合にのみ XML ファイルを生成します。

            // ソートします。
            Collections.sort(strutsFormInfoList, new Comparator<StrutsFormInfo>() {
                //@Override
                public int compare(StrutsFormInfo o1, StrutsFormInfo o2) {
                    return o1.form.compareTo(o2.form);
                }
            });

            new File(info.getTargetDir() + "/struts").mkdirs();

            final StringBuilder strutsConfigBody = new StringBuilder();
            strutsConfigBody.append("  <form-beans>\n");
            for (StrutsFormInfo formInfo : strutsFormInfoList) {
                if (formInfo.description != null) {
                    strutsConfigBody.append("    <!-- "
                            + BlancoJavaSourceUtil.escapeStringAsJavaDoc(formInfo.description) + " -->\n");
                }
                strutsConfigBody.append("    <form-bean type=\"" + formInfo.form + "\" name=\""
                        + BlancoNameUtil.trimJavaPackage(formInfo.form) + "\" />\n\n");
            }
            strutsConfigBody.append("  </form-beans>");

            try {
                BlancoFileUtil.bytes2File(Blanco2gStrutsConfigUtil.getStrutsConfigXml(strutsConfigBody.toString()),
                        new File(info.getTargetDir() + "/struts/struts-config-form-blanco.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        strutsFormInfoList.clear();
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public boolean processClass(final Blanco2gProcessingInfo info) {
        boolean isModified = false;
        for (String anno : info.getInput().getCgClass().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.struts.BlancoStrutsForm")
                    || annotationTypeName.equals("BlancoStrutsForm")) {
                // Struts ActionForm については、強制的に生成フラグを ON にします。
                isModified = true;

                {
                    final StrutsFormInfo form = new StrutsFormInfo();
                    form.form = info.getOutput().getCgSource().getPackage() + "."
                            + info.getOutput().getCgClass().getName();
                    strutsFormInfoList.add(form);

                    form.description = info.getInput().getCgClass().getDescription();
                    if (form.description == null
                            && info.getInput().getCgClass().getLangDoc().getDescriptionList().size() > 0) {
                        form.description = info.getInput().getCgClass().getLangDoc().getDescriptionList().get(0);
                    }
                }

                {
                    // Constants for Serializable
                    final BlancoCgField cgField = info.getFactory().createField("serialVersionUID", "long",
                            "シリアルバージョン UID.");
                    info.getOutput().getCgClass().getFieldList().add(cgField);
                    cgField.setDefault("1L");
                    cgField.setStatic(true);
                    cgField.setFinal(true);
                }

                processReset(info);
            }
        }

        return isModified;
    }

    void processReset(final Blanco2gProcessingInfo info) {
        boolean isResetExists = false;
        final BlancoCgMethod cgMethod = info.getFactory().createMethod("reset", "リセット");

        {
            final BlancoCgParameter cgParameter = info.getFactory().createParameter("mapping",
                    "org.apache.struts.action.ActionMapping", "アクション・マッピング.");
            cgMethod.getParameterList().add(cgParameter);
        }
        {
            final BlancoCgParameter cgParameter = info.getFactory().createParameter("request",
                    "javax.servlet.http.HttpServletRequest", "リクエスト.");
            cgMethod.getParameterList().add(cgParameter);
        }

        for (BlancoCgField cgField : info.getInput().getCgClass().getFieldList()) {
            // static フィールドは処理対象外
            if (cgField.getStatic())
                continue;
            // 万が一 final フィールドがあれば、これは処理対象外
            if (cgField.getFinal())
                continue;

            for (String anno : cgField.getAnnotationList()) {
                final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
                if (annotationTypeName.equals("blanco.struts.BlancoStrutsReset")
                        || annotationTypeName.equals("BlancoStrutsReset")) {
                    isResetExists = true;
                    if (cgField.getDefault() == null) {
                        cgMethod.getLineList().add(
                                "// FIXME @BlancoStrutsForm: Field '" + cgField.getName()
                                        + "' should have initializer.");
                    } else {
                        cgMethod.getLineList().add(cgField.getName() + " = " + cgField.getDefault() + ";");
                    }
                }
            }
        }

        if (isResetExists) {
            info.getOutput().getCgClass().getMethodList().add(cgMethod);
        }
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
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

class StrutsFormInfo {
    String form = null;
    String description = null;
}
