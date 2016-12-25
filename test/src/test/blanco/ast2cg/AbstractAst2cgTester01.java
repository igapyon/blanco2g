package test.blanco.ast2cg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import blanco.noop.BlancoNoop;

/**
 * BlancoEclipseAst2cg が内部クラスのメソッドを適切に処理できることを確認。
 * 
 * @author Toshiki IGA
 */
public abstract class AbstractAst2cgTester01 {
    @BlancoNoop
    public void hello() {
        final List<String> list = new ArrayList<String>();
        Collections.sort(list, new Comparator<String>() {
            //@Override
            public int compare(String arg0, String arg1) {
                return compare(arg0, arg1);
            }
        });
    }
}
