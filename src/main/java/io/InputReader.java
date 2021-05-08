package io;

import java.io.FileReader;
import java.io.IOException;

public class InputReader implements io.interfaces.InputReader {

    private FileReader reader;

    public InputReader() {
    }


    @Override
    public FileReader readFile() throws IOException {
        return this.reader = new FileReader("src/jsonFile/data.json");
    }
}
