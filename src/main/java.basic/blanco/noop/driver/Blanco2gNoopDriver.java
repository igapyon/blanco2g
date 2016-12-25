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
package blanco.noop.driver;

import java.util.List;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gInjectProcessor;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * 「何もしない」ための実装。
 * 
 * なお、このアノテーションがついていると、その箇所の自動生成が強制されます。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gNoopDriver implements Blanco2gDriver, Blanco2gProcessor, Blanco2gInjectProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gNoopDriver());
    }

    //@Override
    public int getPriority() {
        return 1000;
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
        return isMyParameter(info.getInput().getCgClass().getAnnotationList());
    }

    //@Override
    public boolean processField(Blanco2gProcessingInfo info) {
        return isMyParameter(info.getInput().getCgField().getAnnotationList());
    }

    //@Override
    public boolean canProcessMethodParameter(final Blanco2gProcessingInfo info, final BlancoCgParameter cgParameterInput) {
        return isMyParameter(cgParameterInput.getAnnotationList());
    }

    //@Override
    public boolean preCallMethod(final Blanco2gProcessingInfo info) {
        return isMyParameter(info.getInput().getCgMethod().getAnnotationList());
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        return isMyParameter(info.getInput().getCgMethod().getAnnotationList());
    }

    public static boolean isMyParameter(final List<String> annotationList) {
        boolean isMyParameter = false;
        for (String annoParam : annotationList) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + annoParam);
            if (annotationTypeName.equals("blanco.noop.BlancoNoop") || annotationTypeName.equals("BlancoNoop")) {
                isMyParameter = true;
            }
        }
        return isMyParameter;
    }
}
