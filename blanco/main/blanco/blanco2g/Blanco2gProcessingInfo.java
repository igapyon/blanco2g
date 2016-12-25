/*
 * このクラスは 'AbstractBlanco2gProcessingInfo' の具象クラスとして blanco Framework によって自動生成されました。
 */
package blanco.blanco2g;

import java.util.List;
import java.util.Map;

import blanco.cg.BlancoCgObjectFactory;
import blanco.fw.BlancoGeneratedBy;

/**
 * Blanco2g の処理中情報を蓄えるためのクラスです。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class Blanco2gProcessingInfo extends AbstractBlanco2gProcessingInfo {
    /**
     * 指定したパスから読み込んだソースコードのリスト。
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public List<blanco.cg.valueobject.BlancoCgSourceFile> getSourceList() {
        return sourceList;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public BlancoCgObjectFactory getFactory() {
        return factory;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public static Map<java.lang.String, java.lang.String> getGlobalSetting() {
        return globalSetting;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public Blanco2gProcessingInfoCursor getInput() {
        return input;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param input 設定したい値。
     */
    public void setInput(final Blanco2gProcessingInfoCursor input) {
        this.input = input;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public Blanco2gProcessingInfoCursor getOutput() {
        return output;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param output 設定したい値。
     */
    public void setOutput(final Blanco2gProcessingInfoCursor output) {
        this.output = output;
    }

    /**
     * ソースコード生成ターゲットディレクトリ。
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public String getTargetDir() {
        return targetDir;
    }

    /**
     * ソースコード生成ターゲットディレクトリ。
     * [@BlancoGetterSetter]
     *
     * @param targetDir 設定したい値。
     */
    public void setTargetDir(final String targetDir) {
        this.targetDir = targetDir;
    }
}
