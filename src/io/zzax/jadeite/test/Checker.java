package io.zzax.jadeite.test;

import io.zzax.jadeite.basic.Basic;
import io.zzax.jadeite.console.Console;

import java.util.LinkedList;

public class Checker {

    private static LinkedList<String> descriptions = new LinkedList<>();
    private static LinkedList<Integer> counter = new LinkedList<>();

    public static String correctSign = "✅";
    public static String errorSign = "❌";

    static {
        counter.addLast(1);
    }

    private static void pushDescription(String situation) {
        descriptions.addLast(situation);
        counter.addLast(1);
    }

    private static void popDescription() {
        descriptions.removeLast();
        counter.removeLast();
        int count = counter.removeLast();
        count++;
        counter.addLast(count);
    }

    public static void describe(String description, Runnable runnable){
        pushDescription(description);
        runnable.run();
        popDescription();
    }

    public static void task(String description, Task task) {
        pushDescription(description);
        printTaskTitle();
        TaskChecker taskChecker = new TaskChecker();
        boolean result = true;
        try {
            task.run(taskChecker);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        result = result && taskChecker.pass;

        Console.println(result ? correctSign : errorSign);
        popDescription();
    }

    private static void printTaskTitle() {
        String title = String.join(" > ", descriptions);
        Console.printHeader2(title);
    }

    public static void task(Task task) {
        task("task " + counter.getLast(), task);
    }

    @FunctionalInterface
    public interface Task {
        void run(TaskChecker task) throws Exception;
    }

    public static class TaskChecker {
        private boolean pass = true;

        public void when(String name, Object object) {
            Console.println(name + ": " + Basic.toString(object));
        }

        public void check(String on, Object actual, Object expected){
            Console.println();
            boolean correct = Basic.equals(actual, expected);
            pass = pass && correct;
            String topic = on == null ? "" : on;
            Console.println(String.format("checking %s:", topic));

            if (correct) {
                Console.println("  got: " + Basic.toString(actual));
            } else {
                Console.println("  actual: " + Basic.toString(actual));
                Console.println("  expect: " + Basic.toString(expected));

            }
        }
    }
}
