package main.MySQL;

import main.Haupt;

import java.awt.*;
import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class mysql {

    static String user = "root";
    static String password = "";
    static String url = "";
/*
    public static void ReadMySql() {
        //String url = "jdbc:mysql://localhost:3306?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        //String user = "root";
        //String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Erfolgreich mit Datenbank verbunden.");

            // Einfügen/ Verändern
            //String query = "INSERT INTO personen (vorname, nachname, geburtsdatum) VALUES ('Vin', 'Diesel', '1968-04-18')";
            String query = "";

            Statement stmt = conn.createStatement();
            stmt.execute(query);
            stmt.close();


            //------------------------------------------------------------------------------------------------
            // ausgeben
            query = "SELECT * FROM personen ORDER BY personen_id ASC";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int columns = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columns; i++)
                System.out.print(String.format("%-15s", rs.getMetaData().getColumnLabel(i)));

            System.out.println();
            System.out.println("----------------------------------------------------------------");

            while (rs.next()) {
                for (int i = 1; i <= columns; i++)
                    System.out.print(String.format("%-15s", rs.getString(i)));
                System.out.println();
            }

            rs.close();
            stmt.close();


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
    }
    */

    public static String ExecuteMySql(String comand){
        //url = "jdbc:raspberrypi://localhost:3306/test?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        //String user = "root@%";
        //String password = "1234";

        //ResultSet rs = null;
        Boolean rs;
        //javax.sql.DataSource
        /*try{
        Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }*/

        System.out.println("Connect...");
        try (Connection conn = DriverManager.getConnection(Haupt.url, user, Haupt.password)){

            System.out.println("Erfolgreich mit Datenbank verbunden.");

            String query = comand;
            Statement stmt = conn.createStatement();
            rs = stmt.execute(query);

            comand = "MySql: " + rs;//rs.toString();

            //rs.close();
            stmt.close();


        } catch (SQLException ex) {

            //System.err.println(ex.getMessage());
            comand = "Error: " + ex.getMessage();
            System.err.println(comand);
            return comand;

        }

        return comand;
    }

    public static String QuarryListMySql(String comand) {
        //url = "jdbc:mysql://raspberrypi:3306/test?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";


        ResultSet rs;
        //Boolean rs;
        try (Connection conn = DriverManager.getConnection(Haupt.url, user, Haupt.password)) {

            System.out.println("Erfolgreich mit Datenbank verbunden.");

            String query = comand;
            System.out.println(query);
            //System.out.println("1");
            Statement stmt = conn.createStatement();
            //System.out.println("2");
            rs = stmt.executeQuery(query);
            //System.out.println("3");


            comand = "MySql: \n";
            Integer columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++){
                    comand = comand + rs.getString(i) + " ";

                }
                comand = comand + "\n";


            }

            //comand = comand + rs.to

            rs.close();
            stmt.close();


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            comand = "Error: " + ex.getMessage();

        }
        return comand;
    }

    public static List QuarryLineMySql(String comand) {
        //url = "jdbc:mysql://raspberrypi:3306/test?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        //String user = "root";
        //String password = "1234";
        List reply = new List();

        ResultSet rs;
        //Boolean rs;
        try (Connection conn = DriverManager.getConnection(Haupt.url, user, Haupt.password)) {

            System.out.println("Erfolgreich mit Datenbank verbunden.");

            String query = comand;
            //System.out.println(query);
            //System.out.println("1");
            Statement stmt = conn.createStatement();
            //System.out.println("2");
            rs = stmt.executeQuery(query);
            //System.out.println("3");



            Integer columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++){
                    reply.add(rs.getString(i));
                }


            }

            //comand = comand + rs.to

            rs.close();
            stmt.close();


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            System.out.println( "Error: " + ex.getMessage());

        }
        return reply;
    }

    public static String QuarryItemMySql(String comand) {
        //url = "jdbc:mysql://raspberrypi:3306/test";//?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        //String user = "root";
        //String password = "1234";
        List reply = new List();

        ResultSet rs;
        //Boolean rs;
        try (Connection conn = DriverManager.getConnection(Haupt.url, user, Haupt.password)) {

            System.out.println("Erfolgreich mit Datenbank verbunden.");

            String query = comand;
            //System.out.println(query);
            //System.out.println("1");
            Statement stmt = conn.createStatement();
            //System.out.println("2");
            rs = stmt.executeQuery(query);
            //System.out.println("3");



            Integer columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columns; i++){
                    reply.add(rs.getString(i));
                }


            }

            //comand = comand + rs.to

            rs.close();
            stmt.close();


        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
            System.out.println( "Error: " + ex.getMessage());

        }
        if(reply.getItem(0)== ""){
            return "0";
        }else return reply.getItem(0);
    }
}

