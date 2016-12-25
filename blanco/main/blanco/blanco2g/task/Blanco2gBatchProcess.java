/*
 * このソースコードは blanco Frameworkにより自動生成されました。
 */
package blanco.blanco2g.task;

import java.io.IOException;

import blanco.blanco2g.task.valueobject.Blanco2gProcessInput;

/**
 * バッチ処理クラス [Blanco2gBatchProcess]。
 *
 * <P>バッチ処理の呼び出し例。</P>
 * <code>
 * java -classpath (クラスパス) blanco.blanco2g.task.Blanco2gBatchProcess -help
 * </code>
 */
public class Blanco2gBatchProcess {
    /**
     * 正常終了。
     */
    public static final int END_SUCCESS = 0;

    /**
     * 入力異常終了。内部的にjava.lang.IllegalArgumentExceptionが発生した場合。
     */
    public static final int END_ILLEGAL_ARGUMENT_EXCEPTION = 7;

    /**
     * 入出力例外終了。内部的にjava.io.IOExceptionが発生した場合。
     */
    public static final int END_IO_EXCEPTION = 8;

    /**
     * 異常終了。バッチの処理開始に失敗した場合、および内部的にjava.lang.Errorまたはjava.lang.RuntimeExceptionが発生した場合。
     */
    public static final int END_ERROR = 9;

    /**
     * コマンドラインから実行された際のエントリポイントです。
     *
     * @param args コンソールから引き継がれた引数。
     */
    public static final void main(final String[] args) {
        final Blanco2gBatchProcess batchProcess = new Blanco2gBatchProcess();

        // バッチ処理の引数。
        final Blanco2gProcessInput input = new Blanco2gProcessInput();

        boolean isNeedUsage = false;
        boolean isFieldSourcedirProcessed = false;

        // コマンドライン引数の解析をおこないます。
        for (int index = 0; index < args.length; index++) {
            String arg = args[index];
            if (arg.startsWith("-verbose=")) {
                input.setVerbose(Boolean.valueOf(arg.substring(9)).booleanValue());
            } else if (arg.startsWith("-sourcedir=")) {
                input.setSourcedir(arg.substring(11));
                isFieldSourcedirProcessed = true;
            } else if (arg.startsWith("-sourcedir2=")) {
                input.setSourcedir2(arg.substring(12));
            } else if (arg.startsWith("-sourcedir3=")) {
                input.setSourcedir3(arg.substring(12));
            } else if (arg.startsWith("-sourcedir4=")) {
                input.setSourcedir4(arg.substring(12));
            } else if (arg.startsWith("-sourcedir5=")) {
                input.setSourcedir5(arg.substring(12));
            } else if (arg.startsWith("-targetdir=")) {
                input.setTargetdir(arg.substring(11));
            } else if (arg.startsWith("-encoding=")) {
                input.setEncoding(arg.substring(10));
            } else if (arg.equals("-?") || arg.equals("-help")) {
                usage();
                System.exit(END_SUCCESS);
            } else {
                System.out.println("Blanco2gBatchProcess: 入力パラメータ[" + arg + "]は無視されました。");
                isNeedUsage = true;
            }
        }

        if (isNeedUsage) {
            usage();
        }

        if( isFieldSourcedirProcessed == false) {
            System.out.println("Blanco2gBatchProcess: 処理開始失敗。入力パラメータ[input]の必須フィールド値[sourcedir]に値が設定されていません。");
            System.exit(END_ILLEGAL_ARGUMENT_EXCEPTION);
        }

        int retCode = batchProcess.execute(input);

        // 終了コードを戻します。
        // ※注意：System.exit()を呼び出している点に注意してください。
        System.exit(retCode);
    }

    /**
     * 具体的なバッチ処理内容を記述するためのメソッドです。
     *
     * このメソッドに実際の処理内容を記述します。
     *
     * @param input バッチ処理の入力パラメータ。
     * @return バッチ処理の終了コード。END_SUCCESS, END_ILLEGAL_ARGUMENT_EXCEPTION, END_IO_EXCEPTION, END_ERROR のいずれかの値を戻します。
     * @throws IOException 入出力例外が発生した場合。
     * @throws IllegalArgumentException 入力値に不正が見つかった場合。
     */
    public int process(final Blanco2gProcessInput input) throws IOException, IllegalArgumentException {
        // 入力パラメータをチェックします。
        validateInput(input);

        // この箇所でコンパイルエラーが発生する場合、Blanco2gProcessインタフェースを実装して blanco.blanco2g.taskパッケージに Blanco2gProcessImplクラスを作成することにより解決できる場合があります。
        final Blanco2gProcess process = new Blanco2gProcessImpl();

        // 処理の本体を実行します。
        final int retCode = process.execute(input);

        return retCode;
    }

    /**
     * クラスをインスタンス化してバッチを実行する際のエントリポイントです。
     *
     * このメソッドは下記の仕様を提供します。
     * <ul>
     * <li>メソッドの入力パラメータの内容チェック。
     * <li>IllegalArgumentException, RuntimeException, Errorなどの例外をcatchして戻り値へと変換。
     * </ul>
     *
     * @param input バッチ処理の入力パラメータ。
     * @return バッチ処理の終了コード。END_SUCCESS, END_ILLEGAL_ARGUMENT_EXCEPTION, END_IO_EXCEPTION, END_ERROR のいずれかの値を戻します。
     * @throws IllegalArgumentException 入力値に不正が見つかった場合。
     */
    public final int execute(final Blanco2gProcessInput input) throws IllegalArgumentException {
        try {
            // バッチ処理の本体を実行します。
            int retCode = process(input);

            return retCode;
        } catch (IllegalArgumentException ex) {
            System.out.println("Blanco2gBatchProcess: 入力例外が発生しました。バッチ処理を中断します。:" + ex.toString());
            // 入力異常終了。
            return END_ILLEGAL_ARGUMENT_EXCEPTION;
        } catch (IOException ex) {
            System.out.println("Blanco2gBatchProcess: 入出力例外が発生しました。バッチ処理を中断します。:" + ex.toString());
            // 入力異常終了。
            return END_IO_EXCEPTION;
        } catch (RuntimeException ex) {
            System.out.println("Blanco2gBatchProcess: ランタイム例外が発生しました。バッチ処理を中断します。:" + ex.toString());
            ex.printStackTrace();
            // 異常終了。
            return END_ERROR;
        } catch (Error er) {
            System.out.println("Blanco2gBatchProcess: ランタイムエラーが発生しました。バッチ処理を中断します。:" + er.toString());
            er.printStackTrace();
            // 異常終了。
            return END_ERROR;
        }
    }

    /**
     * このバッチ処理クラスの使い方の説明を標準出力に示すためのメソッドです。
     */
    public static final void usage() {
        System.out.println("Blanco2gBatchProcess: Usage:");
        System.out.println("  java blanco.blanco2g.task.Blanco2gBatchProcess -verbose=値1 -sourcedir=値2 -sourcedir2=値3 -sourcedir3=値4 -sourcedir4=値5 -sourcedir5=値6 -targetdir=値7 -encoding=値8");
        System.out.println("    -verbose");
        System.out.println("      説明[verboseモードで動作させるかどうか。]");
        System.out.println("      型[真偽]");
        System.out.println("      デフォルト値[false]");
        System.out.println("    -sourcedir");
        System.out.println("      説明[入力ディレクトリ。Java ソースの必要があります。]");
        System.out.println("      型[文字列]");
        System.out.println("      必須パラメータ");
        System.out.println("    -sourcedir2");
        System.out.println("      説明[追加の入力ディレクトリ。Java ソースの必要があります。]");
        System.out.println("      型[文字列]");
        System.out.println("    -sourcedir3");
        System.out.println("      説明[追加の入力ディレクトリ。Java ソースの必要があります。]");
        System.out.println("      型[文字列]");
        System.out.println("    -sourcedir4");
        System.out.println("      説明[追加の入力ディレクトリ。Java ソースの必要があります。]");
        System.out.println("      型[文字列]");
        System.out.println("    -sourcedir5");
        System.out.println("      説明[追加の入力ディレクトリ。Java ソースの必要があります。]");
        System.out.println("      型[文字列]");
        System.out.println("    -targetdir");
        System.out.println("      型[文字列]");
        System.out.println("      デフォルト値[blanco/main]");
        System.out.println("    -encoding");
        System.out.println("      型[文字列]");
        System.out.println("      デフォルト値[UTF-8]");
        System.out.println("    -? , -help");
        System.out.println("      説明[使い方を表示します。]");
    }

    /**
     * このバッチ処理クラスの入力パラメータの妥当性チェックを実施するためのメソッドです。
     *
     * @param input バッチ処理の入力パラメータ。
     * @throws IllegalArgumentException 入力値に不正が見つかった場合。
     */
    public void validateInput(final Blanco2gProcessInput input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("BlancoBatchProcessBatchProcess: 処理開始失敗。入力パラメータ[input]にnullが与えられました。");
        }
        if (input.getSourcedir() == null) {
            throw new IllegalArgumentException("Blanco2gBatchProcess: 処理開始失敗。入力パラメータ[input]の必須フィールド値[sourcedir]に値が設定されていません。");
        }
    }
}
