package testdi;

import java.io.PrintStream;

/**
 * 记录骑士的所有事迹
 */
public class Minstrel {

    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    // 探险之前调用
    public void singBeforeQuest() {
        stream.println("Fa fa fa, 骑士异常勇敢!");
    }

    // 探险之后调用
    public void singAfterQuest() {
        stream.println("勇敢的骑士结束了冒险!");
    }

}
