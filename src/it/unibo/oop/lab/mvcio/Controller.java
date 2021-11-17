package it.unibo.oop.lab.mvcio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 */
public class Controller {

    /*
     * This class must implement a simple controller responsible of I/O access.
     * It considers a single file at a time, and it is able to serialize objects
     * in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the
     * current file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home
     * folder. A String representing the local user home folder can be accessed
     * using System.getProperty("user.home"). The separator symbol (/ on *nix, \
     * on Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods
     * leads to a software that runs correctly on every platform.
     */

    private File currentFile;

    public Controller() {
        final String separator = System.getProperty("file.separator");
        final String path = System.getProperty("user.home") + separator + "output.txt";
        currentFile = new File(path);
    }

    /**
     * Set a file as the current file.
     * 
     * @param f
     *            the file to set as current
     */
    public void setFile(final File f) {
        this.currentFile = f.getAbsoluteFile();
    }

    /**
     * 
     * @return the current file
     */
    public File getFile() {
        return this.currentFile.getAbsoluteFile();
    }

    /**
     * 
     * @return a String representing current file absolute path
     */
    public String getFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    /**
     * Write a String on the current file (UTF-8 encoding).
     * 
     * @param input
     * @throws IOException
     */
    public void write(final String input) throws IOException {
        try (OutputStream stream = new FileOutputStream(currentFile); 
             DataOutputStream output = new DataOutputStream(stream);
        ) {
            output.writeUTF(input);
        }
    }
}
