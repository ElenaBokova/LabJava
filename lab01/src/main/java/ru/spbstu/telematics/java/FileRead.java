package ru.spbstu.telematics.java;

import java.io.FileReader;
import java.io.IOException;

public class FileRead {
	
    public static String readS() throws IOException
    {   String tmp = "";
        FileReader reader = new FileReader("C:\\file.txt");
        int c;
        try {
                 while ((c = reader.read()) != -1)
                {
                tmp += (char) c;
                }
            } catch(IOException ex)
        {
        System.out.println(ex.getMessage());
         }
 return tmp;
    }


}
