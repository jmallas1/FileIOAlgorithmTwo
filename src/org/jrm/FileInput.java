package org.jrm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class model for a FileInput (read)
 * @author mgreen14
 * @version 1.0
 */
public class FileInput {

    private BufferedReader in = null;
    private String fileName;
    private int BUFFER_SIZE = 1000;

    /**
     * Constructor for FileInput class. Takes a file name will throw exception if file does not exist or is unreadable.
     * @param fileName String representation of a full path to a readable file
     */
    public FileInput(String fileName) {
        this.fileName = fileName;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " " + e);
        }
    }

    /**
     * Dump contents of file to sysout
     */
    public void fileRead() {
        String line;
        try {
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("File Read Error: " + fileName + " " + e);
        }
    }

    /**
     * read and return line of file
     * @return String representation of "the next line" in a file
     */
    public String fileReadLine()
    {
        String returnLine = null;
        try {
            in.mark(BUFFER_SIZE); // #thanksStackOverflow!
            returnLine = in.readLine();
        } catch (Exception e) {
            System.out.println("File Read Error: " + fileName + " " + e);
            return null;
        }
        return returnLine;
    }

    /**
     * Close file that is opened by constructor
     */
    public void fileClose() {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Attempt to set file pointer back to previous line
     */
    public void backUpOneLine()
    {
        try {
            in.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}