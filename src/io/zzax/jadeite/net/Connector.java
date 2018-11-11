package io.zzax.jadeite.net;

import java.io.*;
import java.net.Socket;

public class Connector {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Connector(Socket socket) {
        this.socket = socket;
        setup();
    }

    private void setup() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        writer = new PrintWriter(outputStreamWriter);
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            close();
            return null;
        }
    }

    public void writeLine(String data) {
        writer.write(data + "\n");
        writer.flush();
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
