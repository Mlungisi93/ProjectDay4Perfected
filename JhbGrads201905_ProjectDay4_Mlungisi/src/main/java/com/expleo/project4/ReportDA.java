package com.expleo.project4;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportDA {

    private static ArrayList<String> arLines;
    private static String filePath = new File("").getAbsolutePath();

    static ArrayList<String> readAll(String FileName) {
        File file = new File(FileName+".bat");
        arLines= new ArrayList<>(); // read all lines on my file
        try {

            Scanner inputFileReader = new Scanner(new FileReader(file));
            String line;
            int countDelimeters = 0; // will count the ,delimeters
            // Read the file and skip the first line
            while (inputFileReader.hasNextLine()) // Read next line
            {

                line = inputFileReader.nextLine();
                arLines.add(line);

            }
            inputFileReader.close();
        } catch (FileNotFoundException ex) {

            JOptionPane.showMessageDialog(null, "Error: BatchFile.bat " + "does not exist in " + filePath + " directory");
        }


        return arLines;
    }

    static void process()
    {
        if(arLines.size() !=0)
        {
            String [] data;
            String lastLine = arLines.get(arLines.size() -1);

                  //check if this line has ????? meaning it is the first time it runs
            try {
                data = lastLine.split(" ");



                String targetFolder = filePath+"\\target\\site";
                File targetDirectory = new File(targetFolder);

                if(targetDirectory.exists())
                {

                    String replaceLineBy = data[0]+" "+data[1]+" "+data[2]+ " "+"\""+targetFolder+"\""+""+lastLine.substring(lastLine.lastIndexOf(" "));
                    //replace with xcopy /s /i absolutepath with target
                    arLines.set(arLines.size()-1, replaceLineBy);
                }else
                {
                    JOptionPane.showMessageDialog(null, "No report created yeat in"+filePath+" directory");
                }






            }catch (ArrayIndexOutOfBoundsException ex)
            {
JOptionPane.showMessageDialog(null,"Error in reading lines");
            }


        }else
        {
            JOptionPane.showMessageDialog(null, "BatchFile.bat in "+filePath+" is empty");
        }

    }

    static void processMavenBatFile()
    {
        if(arLines.size() !=0)
        {
            String [] data;
            String firstLine = arLines.get(0);

            //check if this line has ????? meaning it is the first time it runs
            try {
                data = firstLine.split(" ");



                String targetFolder = filePath;
                File targetDirectory = new File(targetFolder);

                if(targetDirectory.exists())
                {

                    String replaceLineBy = data[0]+" "+targetFolder;
                    //replace with xcopy /s /i absolutepath with target
                    arLines.set(0, replaceLineBy);
                }else
                {
                    JOptionPane.showMessageDialog(null, "Invalid directory:"+filePath+" directory");
                }






            }catch (ArrayIndexOutOfBoundsException ex)
            {
                JOptionPane.showMessageDialog(null,"Error in reading lines");
            }


        }else
        {
            JOptionPane.showMessageDialog(null, "BatchFile.bat in "+filePath+" is empty");
        }

    }

    static void writeToFile(String fileName)
    {


        try
        {
            File file = new File(fileName+".bat");
            PrintWriter out = new PrintWriter(file);

            for (int i = 0; i < arLines.size(); i++)
            {

                out.println(arLines.get(i));
            }
            out.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null,"There was a problem saving "
                    + "to the data storage device.\n " + ex.getMessage());
        }
    }

    static void runBatchCopy() {

        try
        {
            //ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "BatchFile.bat");
            filePath = filePath+"\\";
            //String filepath2 = "C:\\Users\\7201/Desktop";
            //File dir = new File(filePath+"/");
            //pb.directory(dir);
            //Process p = pb.start();
            File dir = new File(filePath);
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","BatchFile.bat");
            pb.directory(dir);
            Process p = pb.start();
        }catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to run BAtchFile.bat batch file "+ex.getMessage());
        }

    }

    static void runBatchMaven() {

        try
        {
            //ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "BatchFile.bat");
            filePath = filePath+"\\";

            //String filepath2 = "C:\\Users\\7201/Desktop";
            //File dir = new File(filePath+"/");
            //pb.directory(dir);
            //Process p = pb.start();
            File dir = new File(filePath);
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","MavenCommand.bat");
            pb.directory(dir);
            Process p = pb.start();
        }catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Unable to run MavenCommand.bat batch file "+ex.getMessage());
        }

    }




}
