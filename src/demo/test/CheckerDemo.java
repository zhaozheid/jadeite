package demo.test;

import com.sun.tools.javac.comp.Check;
import io.zzax.jadeite.test.Checker;

public class CheckerDemo {
    public static void main(String[] args) {
        Checker.describe("push", () -> {
            Checker.task(task -> {
                task.when("data", new int[]{1, 2});
                task.check("result", 1, 1);
                task.check("data", 2, 1);
            });
            Checker.task(task -> {
                task.when("data", new int[]{1, 2});
                task.check("result", 1, 1);
                task.check("data", 2, 1);
            });
        });
    }
}
