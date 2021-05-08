package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputReader implements io.interfaces.InputReader {

    private FileReader reader;
    private String path;

    public InputReader(String path) {
        this.path = path;
    }

    @Override
    public FileReader readFile() throws IOException {
        return this.reader = new FileReader(this.path);
    }
}
