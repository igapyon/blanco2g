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
import java.util.List;

import blanco.blanco2g.Blanco2gConstants;
import blanco.blanco2g.Blanco2gDriver;
import blanco.blanco2g.Blanco2gDriverManager;
import blanco.blanco2g.Blanco2gProcessingInfo;
import blanco.blanco2g.Blanco2gProcessor;
import blanco.blanco2g.task.valueobject.Blanco2gProcessInput;
import blanco.blanco2g.util.Blanco2gUtil;
import blanco.cg.BlancoCgObjectFactory;
import blanco.cg.BlancoCgTransformer;
import blanco.cg.transformer.BlancoCgTransformerFactory;
import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgException;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgParameter;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.eclipseast2cg.BlancoEclipseAST2CgConstants;

/**
 * Blanco2g Ant タスクの実装のためのエントリーポイント。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gProcessImpl implements Blanco2gProcess {
    // TODO このクラスですが、パッケージ構成上、一つ上が妥当かもしれません。検討すること。

    /**
     * Blanco2g ドライバーを登録します。
     * 
     * FIXME 本来、これをOSGi ベースの実装へと変更したいです。
     */
    void registerDrivers(final Blanco2gProcessingInfo info) {
        if (isDebug())
            System.out.println("Blanco2gProcessImpl#registerDriver");

        try {
            Class.forName("blanco.cache.driver.Blanco2gCacheDriver");
            Class.forName("blanco.constants.driver.Blanco2gConstantsVersionDriver");
            Class.forName("blanco.db.driver.Blanco2gDbConnectionDriver");
            Class.forName("blanco.db.driver.Blanco2gGeneratedByBlancoDbDriver");
            Class.forName("blanco.gettersetter.driver.Blanco2gGetterSetterDriver");
            Class.forName("blanco.jsf.driver.Blanco2gJsfELContextDriver");
            Class.forName("blanco.jsf.driver.Blanco2gJsfELResolverDriver");
            Class.forName("blanco.jsf.driver.Blanco2gJsfFacesContextDriver");
            Class.forName("blanco.jsf.driver.Blanco2gJsfManagedBeanDriver");
            Class.forName("blanco.name.driver.Blanco2gLogicalNameDriver");
            Class.forName("blanco.noop.driver.Blanco2gNoopDriver");
            Class.forName("blanco.notnull.driver.Blanco2gNotNullDriver");
            Class.forName("blanco.setting.driver.Blanco2gGlobalSettingDriver");
            Class.forName("blanco.struts.driver.Blanco2gStrutsFormDriver");
            Class.forName("blanco.struts.driver.Blanco2gStrutsLogicDriver");
            Class.forName("blanco.trace.driver.Blanco2gTraceDriver");
            Class.forName("blanco.trace.driver.Blanco2gTraceMemoryDriver");
            Class.forName("blanco.validate.driver.Blanco2gValidateDriver");
            Class.forName("blanco.validate.driver.Blanco2gValidateMessageDriver");
            Class.forName("blanco.validate.driver.Blanco2gValidateLengthDriver");
            Class.forName("blanco.validate.driver.Blanco2gValidateRangeDriver");
            Class.forName("blanco.validate.driver.Blanco2gValidateRequiredDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Blanco2g 処理のなかの主たるエントリーポイント。
     * 
     * @param input バッチ処理に与えられた入力
     * @return 0: 正常終了, 0以外: 異常終了
     */
    @SuppressWarnings("deprecation")
    //@Override
    public int execute(final Blanco2gProcessInput input) throws IOException, IllegalArgumentException {

        System.out.println("Blanco2g (" + Blanco2gConstants.getVersion() + ")");
        System.out.println(" - BlancoEclipseAST2Cg (" + BlancoEclipseAST2CgConstants.getVersion() + ")");

        final Blanco2gProcessingInfo info = new Blanco2gProcessingInfo();

        // ターゲットディレクトリを記憶。
        info.setTargetDir(input.getTargetdir());

        try {
            Class.forName("blanco.driver.driver.Blanco2gDriverDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Can't load core driver", e);
        }

        // Blanco2g ドライバーを登録します。
        registerDrivers(info);

        // 指定されたディレクトリー以下の Java ソース・ファイルを列挙します。
        final List<File> files = new Blanco2gJavaSourceFileListProcessor().getSourceFileList(input);

        info.getSourceList().addAll(new Blanco2gConvertFile2BlancoCg().convert(files));

        // Begin
        for (int index = 0; index < Blanco2gDriverManager.getDriverList().size(); index++) {
            final Blanco2gDriver driver = Blanco2gDriverManager.getDriverList().get(index);
            if (driver instanceof Blanco2gProcessor) {
                final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                processor.begin(info);
            }
        }

        // preProcess
        for (int index = 0; index < Blanco2gDriverManager.getDriverList().size(); index++) {
            final Blanco2gDriver driver = Blanco2gDriverManager.getDriverList().get(index);
            if (driver instanceof Blanco2gProcessor) {
                final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                processor.preProcess(info);
            }
        }

        Blanco2gDriverManager.showDrivers();

        // process
        final BlancoCgObjectFactory cgFactory = BlancoCgObjectFactory.getInstance();
        for (int index = 0; index < info.getSourceList().size(); index++) {
            final BlancoCgSourceFile cgSourceInput = info.getSourceList().get(index);

            boolean isModified = false;

            info.getInput().setCgSource(cgSourceInput);

            info.getOutput().setCgSource(
                    cgFactory.createSourceFile(cgSourceInput.getPackage(), cgSourceInput.getDescription()));
            info.getOutput().getCgSource().setEncoding(input.getEncoding());

            info.getOutput().getCgSource().getLangDoc().getDescriptionList()
                    .addAll(info.getInput().getCgSource().getLangDoc().getDescriptionList());

            for (BlancoCgClass cgClassInput : cgSourceInput.getClassList()) {
                if (cgClassInput.getName().startsWith("Abstract") == false) {
                    // Abstract から開始しないクラスはソースコード自動生成の対象外です。
                    continue;
                }

                info.getInput().setCgClass(cgClassInput);
                info.getOutput().setCgClass(
                        cgFactory.createClass(cgClassInput.getName().substring("Abstract".length()),
                                cgClassInput.getDescription()));
                info.getOutput().getCgSource().getClassList().add(info.getOutput().getCgClass());
                info.getOutput().getCgClass().getExtendClassList()
                        .add(cgFactory.createType(cgSourceInput.getPackage() + "." + cgClassInput.getName()));

                {
                    if ("false".equals(Blanco2gProcessingInfo.getGlobalSetting().get(
                            Blanco2gUtil.getLocationString(this, "BlancoGeneratedBy"))) == false) {
                        info.getOutput().getCgSource().getImportList().add("blanco.fw.BlancoGeneratedBy");
                        info.getOutput().getCgClass().getAnnotationList().add("BlancoGeneratedBy(name = \"Blanco2g\")");
                    }
                }

                // JavaDoc をコピー。
                info.getOutput().getCgClass().getLangDoc().getDescriptionList()
                        .addAll(info.getInput().getCgClass().getLangDoc().getDescriptionList());

                // クラスごとの処理
                for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
                    if (driver instanceof Blanco2gProcessor) {
                        final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                        if (processor.processClass(info))
                            isModified = true;
                    }
                }

                // クラスの中のフィールドごとの処理
                for (int fieldIndex = 0; fieldIndex < cgClassInput.getFieldList().size(); fieldIndex++) {
                    final BlancoCgField cgFieldInput = cgClassInput.getFieldList().get(fieldIndex);
                    info.getInput().setCgField(cgFieldInput);
                    for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
                        if (driver instanceof Blanco2gProcessor) {
                            final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                            if (processor.processField(info))
                                isModified = true;
                        }
                    }
                }

                // クラスの中のメソッドごとの処理
                for (BlancoCgMethod cgMethodInput : cgClassInput.getMethodList()) {
                    info.getInput().setCgMethod(cgMethodInput);

                    if (processMethod(info)) {
                        isModified = true;

                        if ("private".equals(info.getInput().getCgMethod().getAccess())) {
                            info.getOutput()
                                    .getCgClass()
                                    .getLangDoc()
                                    .getDescriptionList()
                                    .add("FIXME: Blanco2g: Method '" + info.getInput().getCgMethod().getName()
                                            + "' is 'private' but is mark as need to process.");
                        }
                    }
                }
            }

            if (isModified) {
                final BlancoCgTransformer transformer = BlancoCgTransformerFactory.getJavaSourceTransformer();
                transformer.transform(info.getOutput().getCgSource(), new File(input.getTargetdir()));
            }
        }

        // End
        for (int index = 0; index < Blanco2gDriverManager.getDriverList().size(); index++) {
            final Blanco2gDriver driver = Blanco2gDriverManager.getDriverList().get(index);
            if (driver instanceof Blanco2gProcessor) {
                final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                processor.end(info);
            }
        }

        return 0;
    }

    //@Override
    public boolean progress(final String argProgressMessage) {
        System.out.println(argProgressMessage);
        return false;
    }

    boolean processMethod(final Blanco2gProcessingInfo info) {
        // メソッド中の自動変数を一旦クリアします。
        info.methodAutoVariable.clear();

        info.getOutput().setCgMethod(
                info.getFactory().createMethod(info.getInput().getCgMethod().getName(),
                        info.getInput().getCgMethod().getDescription()));
        boolean isModified = false;

        info.getOutput().getCgMethod().setStatic(info.getInput().getCgMethod().getStatic());

        info.getOutput().getCgMethod().getLangDoc().getDescriptionList()
                .addAll(info.getInput().getCgMethod().getLangDoc().getDescriptionList());

        buildThisMethodParameter(info);

        info.getOutput().getCgMethod().setReturn(info.getInput().getCgMethod().getReturn());

        for (BlancoCgException exception : info.getInput().getCgMethod().getThrowList()) {
            // Copy Throw list
            info.getOutput().getCgMethod().getThrowList().add(exception);
        }

        if (info.getOutput().getCgMethod().getReturn() != null
                && "void".equals(info.getOutput().getCgMethod().getReturn().getType().getName()) == false) {
            // 戻り値がある場合には変数を利用します。
            info.getOutput()
                    .getCgMethod()
                    .getLineList()
                    .add(info.getOutput().getCgMethod().getReturn().getType().getName()
                            + (info.getOutput().getCgMethod().getReturn().getType().getGenerics() != null ? info
                                    .getOutput().getCgMethod().getReturn().getType().getGenerics() : "")
                            + " autoValMethodResult;");
        }

        // -- preCall
        if (isDebug())
            System.out.println("Blanco2gProcessImpl#processMethod: preCall");
        for (Blanco2gDriver driver : Blanco2gDriverManager.getDriverList()) {
            if (driver instanceof Blanco2gProcessor) {
                final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                if (processor.preCallMethod(info))
                    isModified = true;
            }
        }

        // @BlancoInject が 1 つでもあれば、そのメソッドは出力すべきです。
        for (BlancoCgParameter cgParameter : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameter))
                isModified = true;
            // FIXME 未処理の @BlancoInject があれば、それをエラーとして報告すべきです。
        }

        // -- call
        if (isDebug())
            System.out.println("Blanco2gProcessImpl#processMethod: call");
        {
            info.getOutput()
                    .getCgMethod()
                    .getLineList()
                    .add((info.getInput().getCgMethod().getReturn() == null
                            || "void".equals(info.getInput().getCgMethod().getReturn().getType().getName()) ? ""
                            : "autoValMethodResult = ")
                            + (info.getInput().getCgMethod().getStatic() == false ? "super." : info.getInput()
                                    .getCgClass().getName()
                                    + ".")
                            + info.getInput().getCgMethod().getName()
                            + "("
                            + getSuperMethodParameterString(info) + ");");
        }

        // -- postCall
        if (isDebug())
            System.out.println("Blanco2gProcessImpl#processMethod: postCall");
        for (int index = Blanco2gDriverManager.getDriverList().size() - 1; index >= 0; index--) {
            // 逆順で処理します。
            final Blanco2gDriver driver = Blanco2gDriverManager.getDriverList().get(index);
            if (driver instanceof Blanco2gProcessor) {
                final Blanco2gProcessor processor = (Blanco2gProcessor) driver;
                if (processor.postCallMethod(info))
                    isModified = true;
            }
        }

        if (info.getOutput().getCgMethod().getReturn() != null
                && "void".equals(info.getOutput().getCgMethod().getReturn().getType().getName()) == false) {
            // 戻り値がある場合には変数を利用します。
            info.getOutput().getCgMethod().getLineList().add("return autoValMethodResult;");
        }

        if (isModified)
            info.getOutput().getCgClass().getMethodList().add(info.getOutput().getCgMethod());

        return isModified;
    }

    static void buildThisMethodParameter(final Blanco2gProcessingInfo info) {
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (Blanco2gUtil.isBlancoInjectParameter(cgParameterInput))
                continue;

            // FIXME 仮で入力側の final 修飾を OFF にしています。本来、オブジェクトをコピーしたうえで変更すべきです。
            cgParameterInput.setFinal(false);

            // @BlancoInject 指定のないパラメータについて、これを引き継ぎます。
            info.getOutput().getCgMethod().getParameterList().add(cgParameterInput);
        }
    }

    static String getSuperMethodParameterString(final Blanco2gProcessingInfo info) {
        final StringBuilder result = new StringBuilder();
        boolean isFirstArg = true;
        for (BlancoCgParameter cgParameterInput : info.getInput().getCgMethod().getParameterList()) {
            if (isFirstArg) {
                isFirstArg = false;
            } else {
                result.append(", ");
            }
            result.append(cgParameterInput.getName());
        }
        return result.toString();
    }

    static boolean isDebug() {
        return Blanco2gUtil.isDebug(Blanco2gProcessImpl.class);
    }
}
