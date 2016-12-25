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
import blanco.blanco2g.Blanco2gValidateProcessor;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;
import blanco.name.driver.Blanco2gLogicalNameDriver;

public class Blanco2gValidateRangeDriver implements Blanco2gDriver, Blanco2gValidateProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gValidateRangeDriver());
    }

    /**
     * Blanco2g ドライバーの優先度を取得します。
     * 
     * これはバリデーションの処理順序にも利用されます。
     * 
     * <ul>
     * <li>必須チェック: 71000 番台</li>
     * <li>型チェック: 72000 番台</li>
     * <li>桁チェック: 73000 番台</li>
     * <li>形式チェック: 74000 番台</li>
     * <li>範囲チェック: 75000 番台</li>
     * </ul>
     * 
     * @return Blanco2g ドライバーにおける処理優先度。
     */
    //@Override
    public int getPriority() {
        return 75010;
    }

    //@Override
    public boolean canProcessStruts() {
        return true;
    }

    //@Override
    public boolean processValidateField(Blanco2gProcessingInfo info, BlancoCgMethod newMethod) {
        String fieldName = info.getInput().getCgField().getName();
        final String logicalName = Blanco2gLogicalNameDriver.getLogicalName(info.getInput().getCgField()
                .getAnnotationList());
        if (logicalName != null) {
            fieldName = Blanco2gUtil.stripQuote(logicalName);
        }

		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

        boolean isProcessed = false;

        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.validate.BlancoValidateRange")
                    || annotationTypeName.equals("BlancoValidateRange")) {

                if ("private".equals(info.getInput().getCgField().getAccess())) {
                    info.getOutput()
                            .getCgClass()
                            .getLangDoc()
                            .getDescriptionList()
                            .add("FIXME [@BlancoValidateRange] Field '" + info.getInput().getCgField().getName()
                                    + "' is skipped due to 'private'.");
                    isProcessed = true;
                    continue;
                }

                newMethod.getLangDoc().getDescriptionList().add("[@BlancoValidateRange]");

                newMethod.getLineList().add("// [@BlancoValidateRange]");

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strMin = Blanco2gUtil.stripQuote(map.get("min"));
                final String strMax = Blanco2gUtil.stripQuote(map.get("max"));

                if ("java.math.BigDecimal".equals(info.getInput().getCgField().getType().getName())) {
                    newMethod.getLineList().add("if (" + info.getInput().getCgField().getName() + " == null) {");
                    newMethod.getLineList().add("// 値がセットされているもののみ検証対象とします。");

                    if (strMin != null && strMin.length() > 0 && strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMin + "\")) < 0" + " || " + info.getInput().getCgField().getName()
                                        + ".compareTo(new BigDecimal(\"" + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMin + " 以上 "
										+ strMax + " 以下で入力してください。\";");
                        newMethod.getLineList().add("}");
                    } else if (strMin != null && strMin.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMin + "\")) < 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMin
										+ " 以上で入力してください。\";");
                        newMethod.getLineList().add("}");
                    } else if (strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMax
										+ " 以下で入力してください。\";");
                        newMethod.getLineList().add("}");
                    }

                    isProcessed = true;
                } else if ("java.lang.String".equals(info.getInput().getCgField().getType().getName())) {
                    newMethod.getLineList().add("if (" + info.getInput().getCgField().getName() + " == null || " + info.getInput().getCgField().getName() + ".length() == 0) {");
                    newMethod.getLineList().add("// 値がセットされているもののみ検証対象とします。");

                    if (strMin != null && strMin.length() > 0 && strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMin + "\")) < 0" + " || new BigDecimal("
                                        + info.getInput().getCgField().getName() + ").compareTo(new BigDecimal(\""
                                        + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMin + " 以上 "
										+ strMax + " 以下で入力してください。\";");
                        newMethod.getLineList().add("}");
                    } else if (strMin != null && strMin.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMin + "\")) < 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMin
										+ " 以上で入力してください。\";");
                        newMethod.getLineList().add("}");
                    } else if (strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"return "
										+ (useLocationInfo == false ? ""
												: "locationInfo + ") + "\"「"
										+ fieldName + "」は " + strMax
										+ " 以下で入力してください。\";");
                        newMethod.getLineList().add("}");
                    }

                    isProcessed = true;
                } else {
                    newMethod.getLineList().add(
                            "FIXME [@BlancoValidateRange] Field '" + info.getInput().getCgField().getName()
                                    + "' is skipped due to unsupported type '"
                                    + info.getInput().getCgField().getType().getName() + "'.");
                }
            }
        }

        return isProcessed;
    }

    //@Override
    public boolean processValidateFieldForStruts(Blanco2gProcessingInfo info, BlancoCgMethod newMethod) {
        String fieldName = info.getInput().getCgField().getName();
        final String logicalName = Blanco2gLogicalNameDriver.getLogicalName(info.getInput().getCgField()
                .getAnnotationList());
        if (logicalName != null) {
            fieldName = Blanco2gUtil.stripQuote(logicalName);
        }

		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

        boolean isProcessed = false;

        String messageLevel = "WARN";
        if (Blanco2gProcessingInfo.getGlobalSetting().get("blanco.validate.struts.message.level") != null) {
            messageLevel = Blanco2gProcessingInfo.getGlobalSetting().get("blanco.validate.struts.message.level");
        }

        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.validate.BlancoValidateRange")
                    || annotationTypeName.equals("BlancoValidateRange")) {

                newMethod.getLangDoc().getDescriptionList().add("[@BlancoValidateRange]");

                newMethod.getLineList().add("// [@BlancoValidateRange]");

                final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);
                final String strMin = Blanco2gUtil.stripQuote(map.get("min"));
                final String strMax = Blanco2gUtil.stripQuote(map.get("max"));

                if ("java.math.BigDecimal".equals(info.getInput().getCgField().getType().getName())) {
                    newMethod.getLineList().add("if (" + info.getInput().getCgField().getName() + " == null) {");
                    newMethod.getLineList().add("// 値がセットされているもののみ検証対象とします。");

                    if (strMin != null && strMin.length() > 0 && strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");

                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMin + "\")) < 0" + " || " + info.getInput().getCgField().getName()
                                        + ".compareTo(new BigDecimal(\"" + strMax + "\")) > 0" + ") {");
						newMethod
								.getLineList()
								.add("final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.minmax"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ")
										+ "\""
										+ fieldName
										+ "\", \""
										+ strMin
										+ "\", \"" + strMax + "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    } else if (strMin != null && strMin.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMin + "\")) < 0" + ") {");
						newMethod.getLineList().add(
								"final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.min"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ") + "\""
										+ fieldName + "\", \"" + strMin
										+ "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    } else if (strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (" + info.getInput().getCgField().getName() + ".compareTo(new BigDecimal(\""
                                        + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.max"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ") + "\""
										+ fieldName + "\", \"" + strMax
										+ "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    }

                    isProcessed = true;
                } else if ("java.lang.String".equals(info.getInput().getCgField().getType().getName())) {
                    newMethod.getLineList().add("if (" + info.getInput().getCgField().getName() + " == null || " + info.getInput().getCgField().getName() + ".length() == 0) {");
                    newMethod.getLineList().add("// 値がセットされているもののみ検証対象とします。");

                    if (strMin != null && strMin.length() > 0 && strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMin + "\")) < 0" + " || new BigDecimal("
                                        + info.getInput().getCgField().getName() + ").compareTo(new BigDecimal(\""
                                        + strMax + "\")) > 0" + ") {");
						newMethod
								.getLineList()
								.add("final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.minmax"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ")
										+ "\""
										+ fieldName
										+ "\", \""
										+ strMin
										+ "\", \"" + strMax + "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    } else if (strMin != null && strMin.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMin + "\")) < 0" + ") {");
						newMethod.getLineList().add(
								"final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.min"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ") + "\""
										+ fieldName + "\", \"" + strMin
										+ "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    } else if (strMax != null && strMax.length() > 0) {
                        info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
                        info.getOutput().getCgSource().getImportList().add("java.math.BigDecimal");
                        newMethod.getLineList().add(
                                "} else if (new BigDecimal(" + info.getInput().getCgField().getName()
                                        + ").compareTo(new BigDecimal(\"" + strMax + "\")) > 0" + ") {");
						newMethod.getLineList().add(
								"final ActionMessage msg = new ActionMessage(\""
										+ "message.blanco.validate.range.max"
										+ (useLocationInfo == false ? ""
												: ".locationinfo")
										+ "\", "
										+ (useLocationInfo == false ? ""
												: "locationInfo, ") + "\""
										+ fieldName + "\", \"" + strMax
										+ "\");");
                        newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                        newMethod.getLineList().add("return false;");
                        newMethod.getLineList().add("}");
                    }

                    isProcessed = true;
                }
            }
        }

        return isProcessed;
    }

    //@Override
    public boolean canProcessJsf() {
        return false;
    }

    //@Override
    public boolean processValidateFieldForJsf(Blanco2gProcessingInfo info, BlancoCgMethod newMethod) {
        return false;
    }
}
