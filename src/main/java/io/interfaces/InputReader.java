package io.interfaces;

import java.io.FileReader;
import java.io.IOException;

public interface InputReader{
    FileReader readFile() throws IOException;
}
