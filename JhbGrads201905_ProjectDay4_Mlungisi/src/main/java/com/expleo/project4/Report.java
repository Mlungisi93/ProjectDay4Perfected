package com.expleo.project4;

import java.util.ArrayList;

public class Report {

// Invoke all my Data Access Methods

    public static ArrayList<String> readAll(String fileName) {

        return ReportDA.readAll(fileName);
    }

    public static void process()
    {
        ReportDA.process();
    }

    public static void writeToFile(String fileName)
    {

        ReportDA.writeToFile(fileName);
    }

    public static void runBatchCopyReport()
    {
        ReportDA.runBatchCopy();
    }

    public static void processMavenBatFile()
    {
        ReportDA.processMavenBatFile();
    }

    public static void runBatchMavenFile()
    {
        ReportDA.runBatchMaven();
    }
}
