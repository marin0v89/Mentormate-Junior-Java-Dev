package io.interfaces;

import java.io.FileWriter;
import java.io.IOException;

public interface OutputWriter {
    void write(String data) throws IOException;
    void flush() throws IOException;
}
