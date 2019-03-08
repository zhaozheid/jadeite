package io.zzax.jadeite.console;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Console {

    /*
     *  Section: Output
     */

    public static void println() {
        System.out.println();
    }

    public static void println(Object object){
        System.out.print(object);
        System.out.println();
    }

    public static void print(Object... objects){
        List<Object> objectList = Arrays.asList(objects);
        List<String> stringList = objectList.stream().map(Object::toString).collect(Collectors.toList());
        String str = String.join("", stringList);
        System.out.print(str);
    }

    public static void printHeader1(Object object){
        println();
        println("====================");
        println(object);
        println("====================");
    }

    public static void printHeader2(Object object){
        println();
        println(object);
        println("--------------------");
    }


    /*
     *  Section: Output
     */

    private static Scanner scanner;
    private static boolean partialLine;

    private static void ensureScanner(){
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public static int readInt(){
        ensureScanner();
        partialLine = true;
        return scanner.nextInt();
    }

    public static double readDouble(){
        ensureScanner();
        partialLine = true;
        return scanner.nextDouble();
    }

    public static String nextLine() {
        ensureScanner();
        if (partialLine) {
            scanner.nextLine();
        }
        partialLine = false;
        return scanner.nextLine();
    }

    public static String restInLine(){
        ensureScanner();
        partialLine = false;
        return scanner.nextLine();
    }
}
