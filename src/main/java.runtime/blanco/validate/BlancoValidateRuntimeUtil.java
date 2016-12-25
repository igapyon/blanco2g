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
package blanco.validate;

/**
 * BlancoValidate シリーズが利用するランタイム・クラスです。
 * 
 * @author Toshiki Iga
 */
public class BlancoValidateRuntimeUtil {
	/**
	 * トリムする文字のリストです。
	 * 
	 * なお、半角空白以下の文字は、この記載とは別に無条件にトリム対象とします。
	 */
	private static final char[] TRIM_CHAR_LIST = new char[] { '\u0020'/* 半角空白 */,
			'\u3000' /* 全角空白 */};

	/**
	 * 与えられた文字列をトリムします。
	 * 
	 * 基本的に java.lang.String#trim() に全角空白を加えたものです。
	 * 
	 * @param source
	 *            トリム対象となる文字列。
	 * @return トリム後の文字列。
	 */
	public static String trim(final String source) {
		if (source == null) {
			return source;
		}

		final char[] valChars = source.toCharArray();

		// トリム対象外の最初の文字を探します。
		int trimLengthFromBegin = 0;
		for (int index = 0; index < valChars.length; index++) {
			if (isTrimChar(valChars[index]) == false) {
				break;
			}

			trimLengthFromBegin++;
		}

		// トリム対象外の最後の文字を探します。
		int trimLengthFromEnd = 0;
		for (int index = valChars.length - 1; index >= 0; index--) {
			if (isTrimChar(valChars[index]) == false) {
				break;
			}

			trimLengthFromEnd++;
		}

		if (trimLengthFromBegin == 0 && trimLengthFromEnd == 0) {
			// ひとつもトリムしない場合は、入力文字列そのままを戻します。
			return source;
		}

		final int totalTrimLength = trimLengthFromBegin + trimLengthFromEnd;
		if (valChars.length < totalTrimLength) {
			// トリムの結果、内容がなくなる場合には、空文字を戻します。
			return "";
		}

		return new String(valChars, trimLengthFromBegin, valChars.length
				- totalTrimLength);
	}

	/**
	 * トリム対象の文字かどうかを判定します。
	 * 
	 * @param checkChar
	 *            トリム対象かどうかを判定する文字。
	 * @return true:トリム対象, false:トリム対象外
	 */
	public static final boolean isTrimChar(final char checkChar) {
		if (checkChar < '\u0020') {
			// 半角空白以下の文字は、無条件にトリム対象とします。
			return true;
		}

		for (int charIndex = 0; charIndex < TRIM_CHAR_LIST.length; charIndex++) {
			if (checkChar == TRIM_CHAR_LIST[charIndex]) {
				return true;
			}
		}

		return false;
	}
}
