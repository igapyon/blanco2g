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
package blanco.blanco2g.util;

import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessingMethodAutoVariable;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * Blanco2g 用のユーティリティ。
 * 
 * @author Toshiki Iga
 */
public class Blanco2gUtil {
    /**
     * 指定したパラメータが BlancoInject のメソッドパラメータであるかどうか判定します。
     * 
     * @param cgParameterInput
     * @return
     */
    public static boolean isBlancoInjectParameter(final BlancoCgParameter cgParameterInput) {
        boolean isBlancoInjectParameter = false;
        for (String annoParam : cgParameterInput.getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + annoParam);
            if (annotationTypeName.equals("blanco.fw.BlancoInject") || annotationTypeName.equals("BlancoInject")) {
                isBlancoInjectParameter = true;
            }
        }
        return isBlancoInjectParameter;
    }

    /**
     * メソッド引数や生成済みの自動変数に 必要なクラスが含まれていればそれを取得します。
     * 
     * @param info
     * @param requiredClass
     * @return
     */
    public static final String findRequiredVarFromMethodInjectCode(final Blanco2gProcessingInfo info,
            final String requiredClass) {
        // メソッド引数に必要な変数が含まれていればそれを取得します。
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (requiredClass.equals(cgParameterInput.getType().getName())) {
                return cgParameterInput.getName();
            }
        }

        // 生成されたメソッドないの自動変数に必要な変数が含まれていればそれを利用します。
        for (Blanco2gProcessingMethodAutoVariable autoVar : info.methodAutoVariable) {
            if (requiredClass.equals(autoVar.classTypeName)) {
                return autoVar.varName;
            }
        }

        return null;
    }

    /**
     * 与えられた情報をもとに「位置文字列」を取得します。
     * 
     * @param target
     * @param key
     * @return
     */
    public static final String getLocationString(final Class<?> target, final String key) {
        return target.getCanonicalName() + "." + key;
    }

    /**
     * 与えられた情報をもとに「位置文字列」を取得します。
     * 
     * @param target
     * @param key
     * @return
     */
    public static final String getLocationString(final Object target, final String key) {
        return getLocationString(target.getClass(), key);
    }

    /**
     * 対象のクラスはデバッグモードで動作しているかどうか。
     * 
     * @param target
     * @return
     */
    public static final boolean isDebug(final Class<?> target) {
        final String value = Blanco2gProcessingInfo.getGlobalSetting().get(
                Blanco2gUtil.getLocationString(target, "debug"));

        if ("true".equals(value))
            return true;

        return false;
    }

    /**
     * 対象のクラスはデバッグモードで動作しているかどうか。
     * 
     * @param target
     * @return
     */
    public static final boolean isDebug(final Object target) {
        return isDebug(target.getClass());
    }

    /**
     * クオート付き文字列からクオートを除去します。
     * 
     * @param stringLiteralWithQuote
     * @return
     */
    public static final String stripQuote(String stringLiteralWithQuote) {
        if (stringLiteralWithQuote == null) {
            return null;
        }

        if (stringLiteralWithQuote.startsWith("\"") == false) {
            System.out.println("Blanco2gUtil#stripQuote(" + stringLiteralWithQuote
                    + "): string is NOT starts with quote.");
        } else {
            stringLiteralWithQuote = stringLiteralWithQuote.substring(1);
        }

        if (stringLiteralWithQuote.endsWith("\"") == false) {
            System.out.println("Blanco2gUtil#stripQuote(" + stringLiteralWithQuote
                    + "): string is NOT ends with quote.");
        } else {
            stringLiteralWithQuote = stringLiteralWithQuote.substring(0, stringLiteralWithQuote.length() - 1);
        }

        return stringLiteralWithQuote;
    }
}
