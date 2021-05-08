package io;

import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter implements io.interfaces.OutputWriter {
    private FileWriter write;
    private String path;

    public OutputWriter(String path) throws IOException {
        this.path = path;
        this.write = new FileWriter(path);
    }


    @Override
    public void write(String data) throws IOException {
        this.write.write(data);
    }

    @Override
    public void flush() throws IOException {
        write.flush();
    }
}
