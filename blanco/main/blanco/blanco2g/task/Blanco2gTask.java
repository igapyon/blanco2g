/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.blanco2g.task;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import blanco.blanco2g.task.valueobject.Blanco2gProcessInput;

/**
 * Apache Antタスク [Blanco2g]のクラス。
 *
 * Blanco2g のための Ant タスクです。<br>
 * このクラスでは、Apache Antタスクで一般的に必要なチェックなどのコーディングを肩代わりします。
 * 実際の処理は パッケージ[blanco.blanco2g.task]にBlanco2gBatchProcessクラスを作成して記述してください。<br>
 * <br>
 * Antタスクへの組み込み例<br>
 * <pre>
 * &lt;taskdef name=&quot;blanco2g&quot; classname=&quot;blanco.blanco2g.task.Blanco2gTask">
 *     &lt;classpath&gt;
 *         &lt;fileset dir=&quot;lib&quot; includes=&quot;*.jar&quot; /&gt;
 *         &lt;fileset dir=&quot;lib.ant&quot; includes=&quot;*.jar&quot; /&gt;
 *     &lt;/classpath&gt;
 * &lt;/taskdef&gt;
 * </pre>
 */
public class Blanco2gTask extends Task {
    /**
     * Blanco2g のための Ant タスクです。
     */
    protected Blanco2gProcessInput fInput = new Blanco2gProcessInput();

    /**
     * フィールド [sourcedir] に値がセットされたかどうか。
     */
    protected boolean fIsFieldSourcedirProcessed = false;

    /**
     * フィールド [sourcedir2] に値がセットされたかどうか。
     */
    protected boolean fIsFieldSourcedir2Processed = false;

    /**
     * フィールド [sourcedir3] に値がセットされたかどうか。
     */
    protected boolean fIsFieldSourcedir3Processed = false;

    /**
     * フィールド [sourcedir4] に値がセットされたかどうか。
     */
    protected boolean fIsFieldSourcedir4Processed = false;

    /**
     * フィールド [sourcedir5] に値がセットされたかどうか。
     */
    protected boolean fIsFieldSourcedir5Processed = false;

    /**
     * フィールド [targetdir] に値がセットされたかどうか。
     */
    protected boolean fIsFieldTargetdirProcessed = false;

    /**
     * フィールド [encoding] に値がセットされたかどうか。
     */
    protected boolean fIsFieldEncodingProcessed = false;

    /**
     * verboseモードで動作させるかどうか。
     *
     * @param arg verboseモードで動作させるかどうか。
     */
    public void setVerbose(final boolean arg) {
        fInput.setVerbose(arg);
    }

    /**
     * verboseモードで動作させるかどうか。
     *
     * @return verboseモードで動作させるかどうか。
     */
    public boolean getVerbose() {
        return fInput.getVerbose();
    }

    /**
     * Antタスクの[sourcedir]アトリビュートのセッターメソッド。
     *
     * 項目番号: 1<br>
     * 入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @param arg セットしたい値
     */
    public void setSourcedir(final String arg) {
        fInput.setSourcedir(arg);
        fIsFieldSourcedirProcessed = true;
    }

    /**
     * Antタスクの[sourcedir]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 1<br>
     * 入力ディレクトリ。Java ソースの必要があります。<br>
     * 必須アトリビュートです。Apache Antタスク上で必ず値が指定されます。<br>
     *
     * @return このフィールドの値
     */
    public String getSourcedir() {
        return fInput.getSourcedir();
    }

    /**
     * Antタスクの[sourcedir2]アトリビュートのセッターメソッド。
     *
     * 項目番号: 2<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @param arg セットしたい値
     */
    public void setSourcedir2(final String arg) {
        fInput.setSourcedir2(arg);
        fIsFieldSourcedir2Processed = true;
    }

    /**
     * Antタスクの[sourcedir2]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 2<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @return このフィールドの値
     */
    public String getSourcedir2() {
        return fInput.getSourcedir2();
    }

    /**
     * Antタスクの[sourcedir3]アトリビュートのセッターメソッド。
     *
     * 項目番号: 3<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @param arg セットしたい値
     */
    public void setSourcedir3(final String arg) {
        fInput.setSourcedir3(arg);
        fIsFieldSourcedir3Processed = true;
    }

    /**
     * Antタスクの[sourcedir3]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 3<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @return このフィールドの値
     */
    public String getSourcedir3() {
        return fInput.getSourcedir3();
    }

    /**
     * Antタスクの[sourcedir4]アトリビュートのセッターメソッド。
     *
     * 項目番号: 4<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @param arg セットしたい値
     */
    public void setSourcedir4(final String arg) {
        fInput.setSourcedir4(arg);
        fIsFieldSourcedir4Processed = true;
    }

    /**
     * Antタスクの[sourcedir4]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 4<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @return このフィールドの値
     */
    public String getSourcedir4() {
        return fInput.getSourcedir4();
    }

    /**
     * Antタスクの[sourcedir5]アトリビュートのセッターメソッド。
     *
     * 項目番号: 5<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @param arg セットしたい値
     */
    public void setSourcedir5(final String arg) {
        fInput.setSourcedir5(arg);
        fIsFieldSourcedir5Processed = true;
    }

    /**
     * Antタスクの[sourcedir5]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 5<br>
     * 追加の入力ディレクトリ。Java ソースの必要があります。<br>
     *
     * @return このフィールドの値
     */
    public String getSourcedir5() {
        return fInput.getSourcedir5();
    }

    /**
     * Antタスクの[targetdir]アトリビュートのセッターメソッド。
     *
     * 項目番号: 6<br>
     *
     * @param arg セットしたい値
     */
    public void setTargetdir(final String arg) {
        fInput.setTargetdir(arg);
        fIsFieldTargetdirProcessed = true;
    }

    /**
     * Antタスクの[targetdir]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 6<br>
     * デフォルト値[blanco/main]が設定されています。Apache Antタスク上でアトリビュートの指定が無い場合には、デフォルト値が設定されます。<br>
     *
     * @return このフィールドの値
     */
    public String getTargetdir() {
        return fInput.getTargetdir();
    }

    /**
     * Antタスクの[encoding]アトリビュートのセッターメソッド。
     *
     * 項目番号: 7<br>
     *
     * @param arg セットしたい値
     */
    public void setEncoding(final String arg) {
        fInput.setEncoding(arg);
        fIsFieldEncodingProcessed = true;
    }

    /**
     * Antタスクの[encoding]アトリビュートのゲッターメソッド。
     *
     * 項目番号: 7<br>
     * デフォルト値[UTF-8]が設定されています。Apache Antタスク上でアトリビュートの指定が無い場合には、デフォルト値が設定されます。<br>
     *
     * @return このフィールドの値
     */
    public String getEncoding() {
        return fInput.getEncoding();
    }

    /**
     * Antタスクのメイン処理。Apache Antから このメソッドが呼び出されます。
     *
     * @throws BuildException タスクとしての例外が発生した場合。
     */
    @Override
    public final void execute() throws BuildException {
        System.out.println("Blanco2gTask begin.");

        // 項目番号[1]、アトリビュート[sourcedir]は必須入力です。入力チェックを行います。
        if (fIsFieldSourcedirProcessed == false) {
            throw new BuildException("必須アトリビュート[sourcedir]が設定されていません。処理を中断します。");
        }

        if (getVerbose()) {
            System.out.println("- verbose:[true]");
            System.out.println("- sourcedir:[" + getSourcedir() + "]");
            System.out.println("- sourcedir2:[" + getSourcedir2() + "]");
            System.out.println("- sourcedir3:[" + getSourcedir3() + "]");
            System.out.println("- sourcedir4:[" + getSourcedir4() + "]");
            System.out.println("- sourcedir5:[" + getSourcedir5() + "]");
            System.out.println("- targetdir:[" + getTargetdir() + "]");
            System.out.println("- encoding:[" + getEncoding() + "]");
        }

        try {
            // 実際のAntタスクの主処理を実行します。
            // この箇所でコンパイルエラーが発生する場合、Blanco2gProcessインタフェースを実装して blanco.blanco2g.taskパッケージに Blanco2gProcessImplクラスを作成することにより解決できる場合があります。
            final Blanco2gProcess proc = new Blanco2gProcessImpl();
            if (proc.execute(fInput) != Blanco2gBatchProcess.END_SUCCESS) {
                throw new BuildException("タスクは異常終了しました。");
            }
        } catch (IllegalArgumentException e) {
            if (getVerbose()) {
                e.printStackTrace();
            }
            throw new BuildException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BuildException("タスクを処理中に例外が発生しました。処理を中断します。" + e.toString());
        } catch (Error e) {
            e.printStackTrace();
            throw new BuildException("タスクを処理中にエラーが発生しました。処理を中断します。" + e.toString());
        }
    }
}
