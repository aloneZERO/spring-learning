package testdi.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 由于单元测试对于控制台输入的测试比较麻烦，
 * 所以自定义一个输出流，方便测试。
 *
 * @author justZero
 * @since 2019/2/4
 */
public class FakePrintStream extends PrintStream {

    private static StringBuffer printBuffer = new StringBuffer();

    public FakePrintStream() {
        super(new ByteArrayOutputStream());
    }

    @Override
    public void println(String string) {
        printBuffer.append(string).append("\n");
    }

    public String getPrintedString() {
        return printBuffer.toString();
    }

    public void clear() {
        printBuffer.delete(0, printBuffer.length());
    }
}
