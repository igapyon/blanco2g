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
package blanco.driver.driver;

import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * Blanco2g Driver を登録するためのドライバー。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gDriverDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gDriverDriver());
    }

    //@Override
    public int getPriority() {
        return 20;
    }

    //@Override
    public void begin(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void end(final Blanco2gProcessingInfo info) {
    }

    //@Override
    public void preProcess(final Blanco2gProcessingInfo info) {
        for (BlancoCgSourceFile cgSource : info.getSourceList()) {
            for (BlancoCgClass cgClass : cgSource.getClassList()) {
                for (String anno : cgClass.getAnnotationList()) {
                    parseAnnotation(anno);
                }

                for (BlancoCgField cgField : cgClass.getFieldList()) {
                    for (String anno : cgField.getAnnotationList()) {
                        parseAnnotation(anno);
                    }
                }

                for (BlancoCgMethod cgMethod : cgClass.getMethodList()) {
                    for (String anno : cgMethod.getAnnotationList()) {
                        parseAnnotation(anno);
                    }
                }
            }
        }
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
        return false;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    public static void parseAnnotation(final String anno) {
        final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
        if (annotationTypeName.equals("blanco.driver.BlancoDriver") || annotationTypeName.equals("BlancoDriver")) {
            final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
            String strClassName = map.get("className");
            if (strClassName != null) {
                strClassName = Blanco2gUtil.stripQuote(strClassName);
                try {
                    Class.forName(strClassName);
                } catch (ClassNotFoundException ex) {
                    System.out.println("@BlancoDriver: Can't load specified driver '" + strClassName + "'.");
                }
            }
        }
    }
}
