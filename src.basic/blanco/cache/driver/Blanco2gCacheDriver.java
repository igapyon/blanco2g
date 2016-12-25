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
package blanco.cache.driver;

import java.util.List;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.commons.util.BlancoNameAdjuster;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

/**
 * 「メソッド・キャッシュ」ための実装。
 * 
 * FIXME 戻り値について、void や Java プリミティブでは利用できません。チェックが必要。
 * 
 * FIXME 引数のうちキャッシュに利用するのはプリミティブ＋String のみとすべきように気がしています。
 * 
 * FIXME 引数が文字列の場合、ダブルクオートなどのエスケープが必要。それ以外は String#valueOf で良しとする。???
 * 
 * @author Toshiki IGA
 */
public class Blanco2gCacheDriver implements Blanco2gDriver, Blanco2gProcessor {
    // TODO WeakHashMap の使用 ON/OFF 機能がほしい。

    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gCacheDriver());
    }

    //@Override
    public int getPriority() {
        return 6000;
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
        if (isMyParameter(info.getInput().getCgMethod().getAnnotationList()) == false) {
            return false;
        }

		final String type = info.getInput().getCgMethod().getReturn().getType()
				.getName()
				+ (info.getInput().getCgMethod().getReturn().getType()
						.getGenerics() != null ? info.getInput().getCgMethod()
						.getReturn().getType().getGenerics() : "");

		{
			// Create cache object class.
			final BlancoCgClass cgClass = info.getFactory().createClass(
					getClassNameForMethodCacheObject(info),
					"Cache object for method '"
							+ info.getOutput().getCgMethod().getName() + "'.");
			cgClass.setAccess("");
			info.getOutput().getCgSource().getClassList().add(cgClass);

			{
				final BlancoCgField cgField = info.getFactory().createField(
						"birthMillisec", "long",
						"Birth millisec for cache object.");
				cgClass.getFieldList().add(cgField);
				cgField.setAccess("");
			}
			{
				final BlancoCgField cgField = info.getFactory().createField(
						"cachedValue", type, "Cached object value.");
				cgClass.getFieldList().add(cgField);
				cgField.setAccess("");
			}
		}

		String strLifetime = String.valueOf(24 * 60 * 60 * 1000);
        for (String annoParam : info.getInput().getCgMethod().getAnnotationList()) {
        	if (isMyParameter(annoParam)) {
                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + annoParam);
                if (map.get("maxperiod") != null) {
                    strLifetime = map.get("maxperiod");
                }
            }
        }

        final BlancoCgField cgField = info.getFactory().createField(
                "blanco2gCacheMethod" + info.getOutput().getCgMethod().getName(),
                "java.util.Map<String, java.lang.ref.SoftReference<" + getClassNameForMethodCacheObject(info) + ">>",
                "[@BlancoCache] Cache object for method '" + info.getOutput().getCgMethod().getName() + "'.");
        info.getOutput().getCgClass().getFieldList().add(cgField);
        cgField.setDefault("java.util.Collections.synchronizedMap(new java.util.HashMap<java.lang.String, java.lang.ref.SoftReference<"
                + getClassNameForMethodCacheObject(info) + ">>(8192))");
        cgField.setFinal(true);
        // set static or not from method accessor.
        cgField.setStatic(info.getInput().getCgMethod().getStatic());

        info.getOutput().getCgMethod().getLineList().add("{");
        info.getOutput().getCgMethod().getLineList().add("// [@BlancoCache] Search cache.");
        info.getOutput()
                .getCgMethod()
                .getLineList()
                .add("final java.lang.ref.SoftReference<" + getClassNameForMethodCacheObject(info) + "> autoValMethodResultCacheReference = "
                        + "blanco2gCacheMethod" + info.getOutput().getCgMethod().getName() + ".get("
                        + getMethodParameterAsString(info, true) + ");");
        info.getOutput().getCgMethod().getLineList().add("if (autoValMethodResultCacheReference != null) {");
        info.getOutput().getCgMethod().getLineList()
                .add("final " + getClassNameForMethodCacheObject(info) + " autoValCacheLookup = autoValMethodResultCacheReference.get();");
        info.getOutput().getCgMethod().getLineList().add("if (autoValCacheLookup != null) {");
        info.getOutput().getCgMethod().getLineList().add("if (Math.abs(System.currentTimeMillis() - autoValCacheLookup.birthMillisec) <= " + strLifetime + ") {");
        info.getOutput().getCgMethod().getLineList().add("// Hit cache.");
        info.getOutput().getCgMethod().getLineList().add("return autoValCacheLookup.cachedValue;");
        info.getOutput().getCgMethod().getLineList().add("}");
        info.getOutput().getCgMethod().getLineList().add("}");
        info.getOutput().getCgMethod().getLineList().add("}");
        info.getOutput().getCgMethod().getLineList().add("}");

        return true;
    }

    //@Override
    public boolean postCallMethod(final Blanco2gProcessingInfo info) {
        if (isMyParameter(info.getInput().getCgMethod().getAnnotationList()) == false) {
            return false;
        }

        info.getOutput().getCgMethod().getLineList().add("{");
        info.getOutput().getCgMethod().getLineList().add("// [@BlancoCache] Remember cache.");
        info.getOutput().getCgMethod().getLineList().add("final " + getClassNameForMethodCacheObject(info) + " autoValCacheObject = new " + getClassNameForMethodCacheObject(info) + "();");
        info.getOutput().getCgMethod().getLineList().add("autoValCacheObject.birthMillisec = System.currentTimeMillis();");
        info.getOutput().getCgMethod().getLineList().add("autoValCacheObject.cachedValue = autoValMethodResult;");
        info.getOutput()
                .getCgMethod()
                .getLineList()
                .add("blanco2gCacheMethod"
                        + info.getOutput().getCgMethod().getName()
                        + ".put("
                        + getMethodParameterAsString(info, false)
                        + ", new java.lang.ref.SoftReference<"
                        + getClassNameForMethodCacheObject(info) + ">(autoValCacheObject));");
        info.getOutput().getCgMethod().getLineList().add("}");

        return true;
    }

    public static boolean isMyParameter(final List<String> annotationList) {
        boolean isMyParameter = false;
        for (String annoParam : annotationList) {
        	if (isMyParameter(annoParam)) {
                isMyParameter = true;
            }
        }
        return isMyParameter;
    }

	static boolean isMyParameter(final String annoParam) {
		final String annotationTypeName = BlancoEclipseASTAnnotationUtil
				.getAnnotationTypeName("@" + annoParam);
		if (annotationTypeName.equals("blanco.cache.BlancoCache")
				|| annotationTypeName.equals("BlancoCache")) {
			return true;
		}
		return false;
	}

    static boolean isMyKeyParameter(final List<String> annotationList) {
        boolean isMyParameter = false;
        for (String annoParam : annotationList) {
        	if (isMyKeyParameter(annoParam)) {
                isMyParameter = true;
            }
        }
        return isMyParameter;
    }

	static boolean isMyKeyParameter(final String annoParam) {
		final String annotationTypeName = BlancoEclipseASTAnnotationUtil
				.getAnnotationTypeName("@" + annoParam);
		if (annotationTypeName.equals("blanco.cache.BlancoCacheKey")
				|| annotationTypeName.equals("BlancoCacheKey")) {
			return true;
		}
		return false;
	}

    String getMethodParameterAsString(final Blanco2gProcessingInfo info, final boolean isReportFixmeToJavaDoc) {
        final StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        boolean isCacheKeyExists = false;
        builder.append("\"\"");
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput))
                continue;

			if (isMyKeyParameter(cgParameterInput.getAnnotationList()) == false) {
				continue;
			}

			isCacheKeyExists = true;

            if (isFirst) {
                isFirst = false;
                builder.append(" + ");
            } else {
                builder.append(" + \"\\t\" + ");
            }

            builder.append(cgParameterInput.getName());
        }

        if (isFirst) {
        } else {
            builder.append(" + \"\"");
        }

		if (isCacheKeyExists == false) {
			if (isReportFixmeToJavaDoc) {
				info.getOutput().getCgMethod().getLangDoc()
						.getDescriptionList()
						.add("FIXME [@BlancoCache] No cache key found. Please set one or more cache key at least.");
			}
		}
        
        return builder.toString();
    }
    
	String getClassNameForMethodCacheObject(final Blanco2gProcessingInfo info) {
		return "CacheObjectFor"
				+ info.getOutput().getCgClass().getName()
				+ BlancoNameAdjuster.toClassName(info.getOutput().getCgMethod()
						.getName());
	}
}
