package org.jrm.test;

import org.jrm.FileInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileInputTest {

    FileInput fi = null;
    String someLine = null;

    @BeforeEach
    void setUp() {
        fi = new FileInput("./src/org/jrm/test/FileInputTest.java");
    }

    @AfterEach
    void tearDown() {
        fi.fileClose();
    }

    @Test
    void fileReadLine()
    {
        someLine = fi.fileReadLine();
        assertEquals("package org.jrm.test;", someLine, "Should be org.jrm.test");
    }

    @Test
    void backUpOneLine()
    {
        assertEquals("package org.jrm.test;", fi.fileReadLine(), "Should be the package line");
        fi.backUpOneLine();
        assertEquals("package org.jrm.test;", fi.fileReadLine(), "Should still be the package line");
    }

    @Test
    void fileToArray() { assertFalse(true); }

    @Test
    void fileToMap() { assertFalse(true); }

    @Test
    void fileToList() { assertFalse(true); }
}