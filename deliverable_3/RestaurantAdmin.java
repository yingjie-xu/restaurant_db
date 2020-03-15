import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Make sure the Postgresql JDBC driver is in your classpath.
 * You can download the JDBC 4 driver from here if required.
 * https://jdbc.postgresql.org/download.html
 *
 * take care of the variables usernamestring and passwordstring to use
 * appropriate database credentials before you compile !
 *
 **/


public class RestaurantAdmin {

    private static String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
    private static String usernamestring = "cs421g62";
    private static String passwordstring = "gpaIS4.0";
    private static boolean notDone = true;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver ( new org.postgresql.Driver() ) ;
        } catch (Exception cnfe){
            System.out.println("Class not found");
        }

        while(notDone) {
            System.out.println("---------- Restaurant Management System ----------");
            System.out.println("Hello, here are five options that you can choose from to try our super cool app!!!");
            System.out.println("1 - Staff");
            System.out.println("2 - Customer");
            System.out.println("3 - Menu");
            System.out.println("4 - Order");
            System.out.println("0 - Quit the application");
            System.out.println("Just type the number of the option you want:");
            int select = sc.nextInt();
            if (select == 0) {
                break;
            } else if (select == 1) {
                staff();
            } else if (select == 2) {
                customer();
            } else if (select == 3) {
                menu();
            } else if (select == 4) {
                order();
            } else {
                System.out.println("Invalid Input, Please try again!");
            }
            System.out.println("---------------------------------");
            System.out.println("Do you want to continue? (y/n): ");
            String input = sc.nextLine(); // this line is not used
            input = sc.nextLine();
            if (!input.toLowerCase().equals("y")) {
                break;
            }
        }
        System.out.println("Thanks for using the restaurant management system!");
    }

    public static void staff() {
        System.out.println("---------- Staff ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Add a chef");
        System.out.println("2 - Add a delivery_guy");
        System.out.println("3 - Add a waiter");
        System.out.println("4 - Modify the salary of a staff");
        System.out.println("5 - Modify the schedule of a staff");
        System.out.println("6 - Modify the proficiency of a chef");
        System.out.println("7 - View all the staff");
        int select = sc.nextInt();
        if (select == 1) {
            addChef();
        }
    }

    public static void addChef() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT sid FROM staff ORDER BY sid DESC LIMIT 1");
            rs.next();
            System.out.println("Max sid = " + rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void customer() {
        System.out.println("---------- Customer ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Add a customer");
        System.out.println("2 - Make a reservation");
        System.out.println("3 - Modify the address of a customer");
        System.out.println("4 - View the customer's info by phone number");
    }

    public static void menu() {
        System.out.println("---------- Menu ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Add a dish");
        System.out.println("2 - Modify the price of a dish");
        System.out.println("3 - View all the dishes");
    }

    public static void order() {
        System.out.println("---------- Order ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Place a dine-in order");
        System.out.println("2 - Place a delivery order");
        System.out.println("3 - View order by oid");
    }
}
