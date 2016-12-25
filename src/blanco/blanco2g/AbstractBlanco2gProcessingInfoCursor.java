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
package blanco.blanco2g;

import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.gettersetter.BlancoGetterSetter;
import blanco.noop.BlancoNoop;

/**
 * Blanco2g の処理中情報を蓄えるためのクラスです。
 * 
 * @author Toshiki IGA
 */
@BlancoNoop
public abstract class AbstractBlanco2gProcessingInfoCursor {
    @BlancoGetterSetter
    protected BlancoCgSourceFile cgSource;

    @BlancoGetterSetter
    protected BlancoCgClass cgClass;

    @BlancoGetterSetter
    protected BlancoCgMethod cgMethod;

    @BlancoGetterSetter
    protected BlancoCgField cgField;
}
