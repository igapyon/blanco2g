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

import blanco.blanco2g.task.valueobject.Blanco2gProcessInput;

class Blanco2gJavaSourceFileListProcessor {
    /**
     * 指定されたディレクトリー以下の Java ソース・ファイルを列挙します。
     * 
     * @param input
     */
    final List<File> getSourceFileList(final Blanco2gProcessInput input) {
        final List<File> javaSourceFiles = new ArrayList<File>();
        javaSourceFiles.addAll(parseDir(new File(input.getSourcedir())));

        // FIXME この実装は正しくない。Ant の標準的な読み込み形式へと訂正したい。
        if (input.getSourcedir2() != null)
            javaSourceFiles.addAll(parseDir(new File(input.getSourcedir2())));
        if (input.getSourcedir3() != null)
            javaSourceFiles.addAll(parseDir(new File(input.getSourcedir3())));
        if (input.getSourcedir4() != null)
            javaSourceFiles.addAll(parseDir(new File(input.getSourcedir4())));
        if (input.getSourcedir5() != null)
            javaSourceFiles.addAll(parseDir(new File(input.getSourcedir5())));

        return javaSourceFiles;
    }

    /**
     * 指定ディレクトリに含まれる Java ソース・ファイルの一覧を取得する処理のエントリーポイント。
     * 
     * @param dir
     * @return
     */
    List<File> parseDir(final File dir) {
        final List<File> javaSourceFiles = getAllFile(dir);
        if (javaSourceFiles == null) {
            return new ArrayList<File>();
        }
        return javaSourceFiles;
    }

    /**
     * 指定ディレクトリに含まれる Java ソース・ファイルの一覧を取得する処理のエントリーポイントの内部処理。
     * 
     * @param dir
     * @return
     */
    public static List<File> getAllFile(final File dir) {
        final List<File> fileList = new ArrayList<File>();
        try {
            parseDir(null, dir, fileList);
        } catch (IOException e) {
            throw new IllegalArgumentException("想定外の例外が発生しました。", e);
        }
        return fileList;
    }

    /**
     * 指定ディレクトリに含まれる Java ソース・ファイルの一覧を取得します。
     * 
     * @param rootDir
     * @param dir
     * @param fileList
     * @throws IOException
     */
    static void parseDir(String rootDir, final File dir, final List<File> fileList) throws IOException {
        if (rootDir == null)
            rootDir = dir.getCanonicalPath();

        final File[] files = dir.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isDirectory()) {
                parseDir(rootDir, file, fileList);
            } else if (file.isFile()) {
                if (file.getName().endsWith(".java") == false) {
                    // Java のソースコードのみを処理対象とします。
                    continue;
                }

                fileList.add(file);
            }
        }
    }
}
