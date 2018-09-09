package org.jrm;

import java.io.*;

/**
 * Class model for a FileOutput (write)
 * @author mgreen14
 * @version 1.0
 */
public class FileOutput {

    Writer out = null;
    private String fileName;

    /**
     * Constructor fot FileOutput class
     * @param fileName String representation of a full path to a writable file
     */
    public FileOutput(String fileName) {
        this.fileName = fileName;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " "  + e);
        }
    }

    /**
     * Used to write the contents of a string to a file
     * @param line String representation of what you want to write to the given file
     */
    public void fileWrite(String line) {
        try {
            out.write(line+"\n");
        }
        catch(Exception e) {
            System.out.println("File Write Error: " + fileName + " "  + e);
        }
    }

    /**
     * Closes file that is opened by constructor
     */
    public void fileClose() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}