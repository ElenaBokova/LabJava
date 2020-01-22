package ru.spbstu.telematics.java;

import static org.junit.Assert.*;
import java.io.*;
import java.io.File;
public class FileReadTest {

    @org.junit.Test
    public void readFileTest() {
        try {

            File tempFile = new File("C:\\file.txt");

            FileWriter writer = new FileWriter(tempFile);
            String expected="Hello, this is your file";
            writer.write(expected);
            String actual=FileRead.readS();
            assertEquals(expected,actual);
            

        } catch (IOException e) {

        }



    }
}