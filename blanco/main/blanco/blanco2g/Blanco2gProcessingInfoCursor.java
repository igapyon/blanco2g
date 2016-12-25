/*
 * このクラスは 'AbstractBlanco2gProcessingInfoCursor' の具象クラスとして blanco Framework によって自動生成されました。
 */
package blanco.blanco2g;

import blanco.cg.valueobject.BlancoCgClass;
import blanco.cg.valueobject.BlancoCgField;
import blanco.cg.valueobject.BlancoCgMethod;
import blanco.cg.valueobject.BlancoCgSourceFile;
import blanco.fw.BlancoGeneratedBy;

/**
 * Blanco2g の処理中情報を蓄えるためのクラスです。
 */
@BlancoGeneratedBy(name = "Blanco2g")
public class Blanco2gProcessingInfoCursor extends AbstractBlanco2gProcessingInfoCursor {
    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public BlancoCgSourceFile getCgSource() {
        return cgSource;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param cgSource 設定したい値。
     */
    public void setCgSource(final BlancoCgSourceFile cgSource) {
        this.cgSource = cgSource;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public BlancoCgClass getCgClass() {
        return cgClass;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param cgClass 設定したい値。
     */
    public void setCgClass(final BlancoCgClass cgClass) {
        this.cgClass = cgClass;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public BlancoCgMethod getCgMethod() {
        return cgMethod;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param cgMethod 設定したい値。
     */
    public void setCgMethod(final BlancoCgMethod cgMethod) {
        this.cgMethod = cgMethod;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @return 取得したい値。
     */
    public BlancoCgField getCgField() {
        return cgField;
    }

    /**
     * [@BlancoGetterSetter]
     *
     * @param cgField 設定したい値。
     */
    public void setCgField(final BlancoCgField cgField) {
        this.cgField = cgField;
    }
}
