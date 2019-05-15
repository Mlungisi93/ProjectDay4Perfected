package com.expleo.project4;

import net.thucydides.core.annotations.Step;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ChinookConnector {

    private static Connection connection;
    private static File fileDatabase;
    private static boolean exit = false;

    public static Connection getConnection() {
        return connection;
    }

    public static boolean isExited() {
        return exit;
    }

    public static void setExit(boolean myExist) {
        exit = myExist;
    }

    //@Step("Check chinook.db Exist")
    public static String checkChinookFileExist(String fileName) {
        File file = new File(fileName + ".db");
        String message = "";
        String filePath = new File("").getAbsolutePath();
        filePath.concat(" path to the property file");
        try {
            assertThat(file.exists(), equalTo(true));
            message = "chinook.db database exist in " + filePath + " directory";
            fileDatabase = file;
        } catch (AssertionError ex) {
            exit = true;
            message += fileName + ".db file does not exist in " + filePath + " directory";
            //set exit to true


        }

        return message;
    }

    // Perform initialisation processing

    private static boolean connectedToChinnok() {
//asssume you called setFileDatabase Fist
        boolean isConnected = false;
        if (fileDatabase != null) {
            try {
                String url = "jdbc:sqlite:" + fileDatabase;
                String userName = "root";
                String password = "password";

                connection = DriverManager.getConnection(url, userName, password);
                isConnected = true;
                //


            } catch (SQLException ex) {

                isConnected = false;


                //

            }
        }

        return isConnected;
    }

    public static String assertConnectionToChinook() {
        String message = "";
        if (exit == false) {
            try {

                assertThat(connectedToChinnok(), equalTo(true));
                message += "\n Connected to chinook.db database";
                connectedToChinnok(); //connect
            } catch (AssertionError ex) {
                message += "\n Could not open the connection: " + ex.getMessage();
                exit = true;
            }
        }


        return message;
    }

    public static void terminated() {
        boolean isClosed = false;
        try {
            if (connection != null) {
                connection.close();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problems with closing:"+ex.getMessage());
        }


    }

    public static String assertChinookClosed() {
        String message = "";
        try {
            assertThat(connectedToChinnok(), equalTo(true));
            message += "/n Closed connection to chinook database";

        } catch (AssertionError ex) {
            message += "/n Could not Closed connection to chinook database";

        }

        return message;
    }

}
