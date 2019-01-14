package ru.testPing;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.TestLibs;
import sun.awt.OSInfo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


import java.io.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class Ping {
    private static final org.apache.log4j.Logger log = Logger.getLogger(Ping.class);
    public static OSType detectedOS;

    public static void main(String[] args) throws UnknownHostException, IOException {


        if(detectedOS == OSType.Windows){
            new MyTimer(10);



        }else  if (detectedOS == OSType.Linux){
            try {
                String address = "infotecs.ru";
                Ping ping = new Ping();
                List<String> commands = new ArrayList<String>();
                commands.add("ping");
                commands.add("-c");
                commands.add("10");
                commands.add(address);
                ping.doCommand(commands);
            }catch (Exception e){
                log.log(Level.ERROR, e.getMessage());

            }
        }else {
            log.info("Cannot run on this OS. Sorry... ");
        }
//        System.out.println(detectedOS);
        
    }
    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    public static void checkOS(){
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
            } else {
                detectedOS = OSType.Other;
            }
        }
    }

    public void doCommand(List<String> command)
            throws IOException
    {
        String s = null;

        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        // read the output from the command
//        System.out.println("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null)
        {
            log.info(s);
        }

        // read any errors from the attempted command
//        System.out.println("Here is the standard error of the command (if any):\n");

        while ((s = stdError.readLine()) != null)
        {
            log.log(Level.ERROR, s);
        }
    }
}
