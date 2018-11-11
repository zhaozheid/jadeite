package io.zzax.jadeite.file;

import java.io.*;

public class FileWriter {
    private File file;
    private PrintWriter printWriter;

    public FileWriter() {
    }

    public FileWriter(File file) {
        setFile(file);
    }

    public static FileWriter on(String path) {
        return new FileWriter(new File(path));
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        connect();
    }

    private void connect(){
        ensureFile();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(file, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void ensureFile(){
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void println(String line){
        printWriter.println(line);
    }

    public void close() {
        printWriter.close();
    }

    public static void write(String content, File file) {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.printWriter.print(content);
        fileWriter.close();
    }
}
