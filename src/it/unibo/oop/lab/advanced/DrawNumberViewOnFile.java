package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class DrawNumberViewOnFile extends DrawNumberViewOnStream implements DrawNumberView {

    public DrawNumberViewOnFile(final File file) throws FileNotFoundException {
        super(new PrintStream(file.getPath()));
    }

}
