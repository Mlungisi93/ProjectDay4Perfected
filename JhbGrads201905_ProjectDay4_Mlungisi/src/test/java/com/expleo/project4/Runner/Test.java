package com.expleo.project4.Runner;

import com.expleo.project4.Report;

import java.io.IOException;

public class Test {
    private static final String MAVENBATCH = "MavenCommand";
    public static void main(String[] args) throws InterruptedException, IOException {
        Report.readAll(MAVENBATCH);
        Report.processMavenBatFile();
        Report.writeToFile(MAVENBATCH);
        Report.runBatchMavenFile();
        Thread.sleep(35000);
        Report.runBatchCopyReport();
        Thread.sleep(4000);
        Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); // to kill any cmd

    }
}
