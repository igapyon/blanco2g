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

import java.util.List;
import java.util.Map;

import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gValidateProcessor;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.eclipseast2cg.util.BlancoEclipseASTAnnotationUtil;

public class Blanco2gValidateMessageDriver implements Blanco2gDriver,
		Blanco2gValidateProcessor {
	static {
		Blanco2gDriverManager
				.registerDriver(new Blanco2gValidateMessageDriver());
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
	// @Override
	public int getPriority() {
		return 70010;
	}

    //@Override
    public boolean canProcessStruts() {
        return true;
    }

    //@Override
    public boolean canProcessJsf() {
        return true;
    }

	// @Override
	public boolean processValidateField(Blanco2gProcessingInfo info,
			BlancoCgMethod newMethod) {
		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

		if (useLocationInfo) {
			final BlancoCgParameter param = info
					.getFactory()
					.createParameter("locationInfo", "java.lang.String",
							"[@BlancoValidateMessage] メッセージの位置情報 (useLocationInfo = true)");
			newMethod.getParameterList().add(param);
		}

		// メソッドにパラメータ追加の変更は加えるものの、ここでは false は戻します。
		return false;
	}

	// @Override
	public boolean processValidateFieldForStruts(Blanco2gProcessingInfo info,
			BlancoCgMethod newMethod) {
		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

		if (useLocationInfo) {
			final BlancoCgParameter param = info
					.getFactory()
					.createParameter("locationInfo", "java.lang.String",
							"[@BlancoValidateMessage] メッセージの位置情報 (useLocationInfo = true)");
			newMethod.getParameterList().add(param);
		}

		// メソッドにパラメータ追加の変更は加えるものの、ここでは false は戻します。
		return false;
	}

	// @Override
	public boolean processValidateFieldForJsf(Blanco2gProcessingInfo info,
			BlancoCgMethod newMethod) {
		final boolean useLocationInfo = Blanco2gValidateMessageDriver
				.useLocationInfo(info.getInput().getCgField()
						.getAnnotationList());

		if (useLocationInfo) {
			final BlancoCgParameter param = info
					.getFactory()
					.createParameter("locationInfo", "java.lang.String",
							"[@BlancoValidateMessage] メッセージの位置情報 (useLocationInfo = true)");
			newMethod.getParameterList().add(param);
		}

		// メソッドにパラメータ追加の変更は加えるものの、ここでは false は戻します。
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
	public static boolean useLocationInfo(
			final List<java.lang.String> annotationList) {
		for (String anno : annotationList) {
			final String annotationTypeName = BlancoEclipseASTAnnotationUtil
					.getAnnotationTypeName("@" + anno);
			if (annotationTypeName
					.equals("blanco.validate.BlancoValidateMessage")
					|| annotationTypeName.equals("BlancoValidateMessage")) {

				final Map<String, String> map = BlancoEclipseASTAnnotationUtil
						.getAnnotationMemberValuePair("@" + anno);
				final String strName = map.get("useLocationInfo");
				if (strName != null && strName.equals("true")) {
					return true;
				}
			}
		}
		return false;
	}
}
