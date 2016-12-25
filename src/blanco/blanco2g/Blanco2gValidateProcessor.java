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
/*******************************************************************************
 * Copyright 2012 Toshiki IGA and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package blanco.blanco2g;

import blanco.cg.valueobject.BlancoCgMethod;

/**
 * Blanco2g の処理エンジン・検証ドライバーのインタフェース。
 * 
 * @author Toshiki IGA
 */
public interface Blanco2gValidateProcessor extends Blanco2gDriver {
    /**
     * Apache Struts の検証を生成できるかどうか。
     * 
     * TODO メソッドではなくインタフェースによる切り替えに変更したい。
     * 
     * @return
     */
    boolean canProcessStruts();

    /**
     * JSF の検証を生成できるかどうか。
     * 
     * TODO メソッドではなくインタフェースによる切り替えに変更したい。
     * 
     * @return
     */
    boolean canProcessJsf();

    /**
     * 一般的なフィールド検証を処理します。
     * 
     * @param info
     * @return
     */
    boolean processValidateField(Blanco2gProcessingInfo info, final BlancoCgMethod newMethod);

    /**
     * Apache Struts 用のフィールド検証を処理します。
     * 
     * @param info
     * @return
     */
    boolean processValidateFieldForStruts(Blanco2gProcessingInfo info, final BlancoCgMethod newMethod);

    /**
     * JSF 用のフィールド検証を処理します。
     * 
     * @param info
     * @param newMethod
     * @return
     */
    boolean processValidateFieldForJsf(Blanco2gProcessingInfo info, final BlancoCgMethod newMethod);
}
