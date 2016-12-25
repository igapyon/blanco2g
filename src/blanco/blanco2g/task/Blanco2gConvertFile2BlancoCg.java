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
package blanco.blanco2g.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.eclipseast2cg.BlancoEclipseAST2CgParser;

/**
 * 与えられたファイルを BlancoCg 形式に変換します。
 * 
 * @author Toshiki IGA
 */
class Blanco2gConvertFile2BlancoCg {
    /**
     * 与えられたファイルを BlancoCg 形式に変換します。
     * 
     * @param fileList
     * @throws IOException
     */
    List<BlancoCgSourceFile> convert(final List<File> fileList) throws IOException {
        final List<BlancoCgSourceFile> result = new ArrayList<BlancoCgSourceFile>();
        for (File sourceFile : fileList) {
            final BlancoCgSourceFile cgSource = new BlancoEclipseAST2CgParser().parse(sourceFile);
            cgSource.setName(sourceFile.getName());

            result.add(cgSource);
        }
        return result;
    }
}
