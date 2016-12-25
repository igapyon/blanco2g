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
package blanco.constants.driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gConstantsVersionDriver implements Blanco2gDriver, Blanco2gProcessor {
    static final boolean IS_DEBUG = true;

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gConstantsVersionDriver());
    }

    //@Override
    public int getPriority() {
        return 10000;
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
        boolean isModified = false;
        for (String anno : info.getInput().getCgClass().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.constants.BlancoConstantsVersion")
                    || annotationTypeName.equals("BlancoConstantsVersion")) {
                final BlancoCgField cgField = info.getFactory().createField("VERSION", "java.lang.String",
                        "Version Number.");
                info.getOutput().getCgClass().getFieldList().add(cgField);

                cgField.getLangDoc().getDescriptionList().add("[@BlancoConstantsVersion]");

                String prefix = "";
                String versionFormat = "yyyyMMddHHmm";
                String suffix = "";
                String getter = "true";
                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                if (map.get("prefix") != null) {
                    prefix = map.get("prefix");
                    prefix = prefix.substring(1, prefix.length() - 1);
                }
                if (map.get("format") != null) {
                    versionFormat = map.get("format");
                    versionFormat = versionFormat.substring(1, versionFormat.length() - 1);
                }
                if (map.get("suffix") != null) {
                    suffix = map.get("suffix");
                    suffix = suffix.substring(1, suffix.length() - 1);
                }

                if (map.get("getter") != null) {
                    getter = map.get("getter");
                }
                if ("true".equals(getter)) {
                    final BlancoCgMethod cgMethod = info.getFactory().createMethod("getVersion",
                            "Getter for version constants.");
                    info.getOutput().getCgClass().getMethodList().add(cgMethod);
                    cgMethod.setStatic(true);
                    cgMethod.setReturn(info.getFactory().createReturn("java.lang.String", "Version string."));
                    cgMethod.getLineList().add("return VERSION;");

                    cgMethod.getLangDoc().getDescriptionList().add("[@BlancoConstantsVersion]");
                }

                // 現在日時を取得します。
                final String now = (new SimpleDateFormat(versionFormat)).format(new Date());

                cgField.setDefault("\"" + prefix + now + suffix + "\"");
                cgField.setAccess("public");
                cgField.setStatic(true);
                cgField.setFinal(true);

                isModified = true;
            }
        }

        return isModified;
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
