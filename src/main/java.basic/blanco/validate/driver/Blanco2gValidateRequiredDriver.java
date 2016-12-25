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
import blanco.commons.util.BlancoNameAdjuster;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;
import blanco.name.driver.Blanco2gLogicalNameDriver;

public class Blanco2gValidateRequiredDriver implements Blanco2gDriver, Blanco2gValidateProcessor {
    static {
        Blanco2gDriverManager.registerDriver(new Blanco2gValidateRequiredDriver());
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
        return 71010;
    }

    //@Override
    public boolean canProcessStruts() {
        return true;
    }

    //@Override
    public boolean processValidateField(Blanco2gProcessingInfo info, BlancoCgMethod method) {
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
            if (annotationTypeName.equals("blanco.validate.BlancoValidateRequired")
                    || annotationTypeName.equals("BlancoValidateRequired")) {

                if ("private".equals(info.getInput().getCgField().getAccess())) {
                    info.getOutput()
                            .getCgClass()
                            .getLangDoc()
                            .getDescriptionList()
                            .add("FIXME [@BlancoValidateRequired] Field '" + info.getInput().getCgField().getName()
                                    + "' is skipped due to 'private'.");
                    isProcessed = true;
                    continue;
                }

                method.getLangDoc().getDescriptionList().add("[@BlancoValidateRequired]");

                method.getLineList().add("// [@BlancoValidateRequired]");
                if ("java.lang.String".equals(info.getInput().getCgField().getType().getName())) {
                    info.getOutput().getCgSource().getImportList().add("blanco.validate.BlancoValidateRuntimeUtil");
                    method.getLineList().add(
                            "if (" + info.getInput().getCgField().getName() + " == null || BlancoValidateRuntimeUtil.trim("
                                    + info.getInput().getCgField().getName() + ").length() == 0) {");
                } else if ("int".equals(info.getInput().getCgField().getType().getName())) {
                    method.getLineList().add("if (" + info.getInput().getCgField().getName() + " == -1) {");
                } else {
                    method.getLangDoc()
                            .getDescriptionList()
                            .add("FIXME [@BlancoValidateRequired] Type '"
                                    + info.getInput().getCgField().getType().getName() + "' is not supported. ");
                }

                if (isSelection(anno) == false) {
					method.getLineList().add(
							"return "
									+ (useLocationInfo == false ? ""
											: "locationInfo + ") + "\"「"
									+ fieldName + "」に値が入力されていません。\";");
                } else {
					method.getLineList().add(
							"return "
									+ (useLocationInfo == false ? ""
											: "locationInfo + ") + "\"「"
									+ fieldName + "」を選択してください。\";");
                }
                method.getLineList().add("}");

                isProcessed = true;
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
            if (annotationTypeName.equals("blanco.validate.BlancoValidateRequired")
                    || annotationTypeName.equals("BlancoValidateRequired")) {

                String messageKey = "message.blanco.validate.required.input";
                if (isSelection(anno)) {
                    messageKey = "message.blanco.validate.required.selection";
                }
                if(useLocationInfo){
                	messageKey = messageKey + ".locationinfo"; 
                }

                newMethod.getLineList().add("// [@BlancoValidateRequired]");
                info.getOutput().getCgSource().getImportList().add("blanco.validate.BlancoValidateRuntimeUtil");
                newMethod.getLineList().add(
                        "if (" + info.getInput().getCgField().getName() + " == null || BlancoValidateRuntimeUtil.trim("
                                + info.getInput().getCgField().getName() + ").length() == 0) {");
                info.getOutput().getCgSource().getImportList().add("org.apache.struts.action.ActionMessage");
				newMethod.getLineList().add(
						"final ActionMessage msg = new ActionMessage(\""
								+ messageKey
								+ "\", "
								+ (useLocationInfo == false ? ""
										: "locationInfo, ") + "\"" + fieldName
								+ "\");");
                newMethod.getLineList().add("actionMessages.add(\"" + messageLevel + "\", msg);");
                newMethod.getLineList().add("return false;");
                newMethod.getLineList().add("}");

                isProcessed = true;
            }
        }

        return isProcessed;
    }

    //@Override
    public boolean canProcessJsf() {
        return true;
    }

    //@Override
    public boolean processValidateFieldForJsf(Blanco2gProcessingInfo info, BlancoCgMethod newMethod) {
        boolean isProcessed = false;

		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

        for (String anno : info.getInput().getCgField().getAnnotationList()) {
            final String annotationTypeName = BlancoEclipseASTAnnotationUtil.getAnnotationTypeName("@" + anno);
            if (annotationTypeName.equals("blanco.validate.BlancoValidateRequired")
                    || annotationTypeName.equals("BlancoValidateRequired")) {

                newMethod.getLineList().add("// [@BlancoValidateRequired]");
				newMethod.getLineList().add(
						"if (validate"
								+ BlancoNameAdjuster.toClassName(info
										.getInput().getCgField().getName())
								+ "("
								+ (useLocationInfo == false ? ""
										: "locationInfo") + ") != null) {");
                info.getOutput().getCgSource().getImportList().add("javax.faces.application.FacesMessage");
				newMethod
						.getLineList()
						.add("final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, validate"
								+ BlancoNameAdjuster.toClassName(info
										.getInput().getCgField().getName())
								+ "("
								+ (useLocationInfo == false ? ""
										: "locationInfo") + "), null);");
                newMethod.getLineList().add("ctx.addMessage(null, msg);");
                newMethod.getLineList().add("return false;");
                newMethod.getLineList().add("}");

                isProcessed = true;
            }
        }

        return isProcessed;
    }

    static boolean isSelection(final String anno) {
        final Map<String, String> map = BlancoEclipseASTAnnotationUtil.getAnnotationMemberValuePair("@" + anno);

        final String strIsSelection = map.get("isSelection");
        if (strIsSelection == null || strIsSelection.equals("true") == false) {
            return false;
        } else {
            return true;
        }
    }
}
