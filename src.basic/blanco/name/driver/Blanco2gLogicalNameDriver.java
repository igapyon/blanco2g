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
package blanco.name.driver;

import java.util.List;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gLogicalNameDriver implements Blanco2gDriver, Blanco2gProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gLogicalNameDriver());
    }

    //@Override
    public int getPriority() {
        return 3000;
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
        return false;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return false;
    }

    /**
     * 当面提供するのは このメソッドのみ。
     * 
     * FIXME: いずれ、このメソッドを直接ではなく、なにかしら記憶用ストレージ経由で論理名を利用側に引き渡すべきです。
     * 
     * @param annotationList
     * @return
     */
    public static String getLogicalName(final List<java.lang.String> annotationList) {
        for (String anno : annotationList) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.name.BlancoLogicalName")
                    || annotationTypeName.equals("BlancoLogicalName")) {

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strName = map.get("name");
                if (strName != null) {
                    return strName;
                }
            }
        }
        return null;
    }
}
