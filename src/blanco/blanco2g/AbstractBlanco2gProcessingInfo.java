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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.gettersetter.BlancoGetterSetter;

/**
 * Blanco2g の処理中情報を蓄えるためのクラスです。
 * 
 * @author Toshiki IGA
 */
public abstract class AbstractBlanco2gProcessingInfo {
    // TODO フィールドについて Getter, Setter 化すること。

    /**
     * 指定したパスから読み込んだソースコードのリスト。
     */
    @BlancoGetterSetter(getter = true, setter = false)
    final List<BlancoCgSourceFile> sourceList = new ArrayList<BlancoCgSourceFile>();

    // 現在処理中の情報。
    @BlancoGetterSetter(getter = true, setter = false)
    protected final BlancoCgObjectFactory factory = BlancoCgObjectFactory.getInstance();

    @BlancoGetterSetter(getter = true, setter = false)
    protected static final Map<String, String> globalSetting = new ConcurrentHashMap<String, String>();

    // TODO 処理中オブジェクトをスタックに積んで、それを戻す必要があるのかを検討すること。

    // TODO Set them as setter-getter

    @BlancoGetterSetter
    protected Blanco2gProcessingInfoCursor input = new Blanco2gProcessingInfoCursor();

    @BlancoGetterSetter
    protected Blanco2gProcessingInfoCursor output = new Blanco2gProcessingInfoCursor();

    public List<Blanco2gProcessingMethodAutoVariable> methodAutoVariable = new ArrayList<Blanco2gProcessingMethodAutoVariable>();

    /**
     * ソースコード生成ターゲットディレクトリ。
     */
    @BlancoGetterSetter
    public String targetDir = null;
}
