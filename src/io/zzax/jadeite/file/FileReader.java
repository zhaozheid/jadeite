package io.zzax.jadeite.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class FileReader implements Iterable<String>{


    /*
     *  Section: Constructors
     */

    public FileReader() {

    }

    public FileReader(File file) {
        setFile(file);
    }

    public static FileReader on(String path) {
        return new FileReader(new File(path));
    }


    /*
     *  Section: Context - Properties
     */

    private File file;
    private Scanner scanner;
    private boolean ignoreEmptyLines;


    /*
     *  Section: Context - Getters & Setters
     */

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;

        if (file == null) {
            scanner = null;
            return;
        }

        try {
            setScanner(new Scanner(file, "utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setScanner(Scanner scanner){
        if (this.scanner != null) {
            this.scanner.close();
        }
        this.scanner = scanner;
    }

    public void setIgnoreEmptyLines(boolean ignoreEmptyLines) {
        this.ignoreEmptyLines = ignoreEmptyLines;
    }

    /*
     *  Section: Line - Methods
     */

    public String nextLine() {
        if (scanner == null) {
            return null;
        }

        String line = scanner.nextLine();

        if (ignoreEmptyLines) {
            while(line != null && line.trim().equals("")){
                line = scanner.nextLine();
            }
        }

        if (line == null) {
            scanner.close();
        }

        return line;
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }


    /*
     *  Section: Iterable - Methods
     */

    @Override
    public Iterator<String> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<String> {
        @Override
        public boolean hasNext() {
            return hasNextLine();
        }

        @Override
        public String next() {
            return nextLine();
        }
    }

    public static String read(File file) {
        StringBuilder builder = new StringBuilder();
        FileReader fileReader = new FileReader(file);
        for (String line : fileReader) {
            builder.append(line + "\n");
        }
        return builder.toString();
    }
}
