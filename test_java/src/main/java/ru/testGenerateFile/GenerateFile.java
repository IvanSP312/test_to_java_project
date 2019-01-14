package ru.testGenerateFile;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.TestLibs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static javax.script.ScriptEngine.FILENAME;

public class GenerateFile {
    private static final org.apache.log4j.Logger log = Logger.getLogger(GenerateFile.class);

    public static void main(String args[]){
        FileWriter file = null;
        try {
            file = new FileWriter("testFile.txt");
            boolean stop =true;
            while (stop){
                File fileCheck = new File("testFile.txt");
                if(fileCheck.exists()){
                    double bytes = fileCheck.length();
                    double kilobytes = bytes / 1024;
                    double mb = kilobytes / 1024;
                    System.out.println(mb);
                    if(mb <= 2 ){
//                        if(file.)
                        System.out.println(kilobytes);
                        file.write('1'+"\n");

                    }else {
                        Exception e = new IOException();
                        log.log(Level.ERROR, e.getMessage());
                        file.close();
                        stop = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
