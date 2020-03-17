/**
 * Group 62 project
 * members: Weiming Guo, Hengxian Jiang, Yingjie Xu, Helen Ren
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

    /**
     * Start the restaurant management system program
     * @param args
     * @author Yingjie Xu
     */
    public static void main(String[] args) {
        try {
            DriverManager.registerDriver ( new org.postgresql.Driver() ) ;
        } catch (Exception cnfe){
            System.out.println("Class not found");
        }

        while(notDone) {
            System.out.println("---------- Restaurant Management System ----------");
            System.out.println("Hello, here are five options that you can choose from to try our super cool app!");
            System.out.println("Each option would have some sub-options to select! Enjoy!");
            System.out.println("1 - Staff");
            System.out.println("2 - Customer");
            System.out.println("3 - Menu");
            System.out.println("4 - Order");
            System.out.println("0 - Quit the application");
            System.out.println("Just type the number of the option you want:");
            int select = sc.nextInt();
            sc.nextLine();
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
            String input = sc.nextLine();
            if (!input.toLowerCase().equals("y")) {
                break;
            }
        }
        System.out.println("Thanks for using the restaurant management system!");
        sc.close();
    }

    /**
     * All possible methods for the admin to modify the staff
     * @author Yingjie Xu
     */
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
        sc.nextLine();
        if (select == 1) {
            addChef();
        } else if (select == 2) {
            addDeliveryGuy();
        } else if (select == 3) {
            addWaiter();
        } else if (select == 4) {
            modifySalary();
        } else if (select == 5) {
            modifySchedule();
        } else if (select == 6) {
            modifyProficiency();
        } else if (select == 7) {
            viewAllStaff();
        } else {
            System.out.println("Invalid selection.");
            return;
        }
    }
    
    /**
     * check if the input phone number is valid
     * @param phone
     * @return boolean
     * @author Yingjie Xu
     */
    private static boolean validatePhone(String phone) {
        boolean validate = phone.matches("\\d{3}-\\d{3}-\\d{4}\\w*\\s*");
        if (!validate) {
            System.out.println("Invalid phone number, please follow the format (xxx-xxx-xxxx)!");
        }
        return validate;
    }

    /**
     * Helper method for addChef, addDeliveryGuy and addWaiter
     * Add a staff into database
     * @return sid
     * @author Yingjie Xu
     */
    public static int addStaff() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT sid FROM staff ORDER BY sid DESC LIMIT 1");
            rs.next();
            int sid = rs.getInt(1);
            sid++;
            System.out.println("----- Info of Staff -----");
            System.out.println("Name of the staff: ");
            String name = sc.nextLine();
            System.out.println("Working schedule of the staff (choose from morning, afternoon, evening): ");
            String schedule = sc.nextLine();
            System.out.println("Salary of the staff: ");
            double salary = sc.nextDouble();
            sc.nextLine();
            int result = statement.executeUpdate("INSERT INTO staff VALUES(" + sid + ",'" + name + "','" + schedule + "'," + salary + ");");
            if (result == 1) {
                System.out.println("New staff added successfully.");
            } else {
                System.out.println("Something went wrong. New staff was not added.");
            }
            return sid;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
        return -1;
    }

    /**
     * Add a chef into database
     * @author Yingjie Xu
     */
    public static void addChef() {
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            int sid = addStaff();
            if (sid == -1) {
                System.out.println("Fail to add the staff.");
                return;
            }
            System.out.println("----- Info of Chef -----");
            System.out.println("Proficiency of the chef (1 - 5): ");
            int proficiency = sc.nextInt();
            sc.nextLine();
            System.out.println("Cooking style of the chef: ");
            String style = sc.nextLine();
            int result = statement.executeUpdate("INSERT INTO chef VALUES(" + proficiency + ",'" + style + "'," + sid + ");");
            if (result == 1) {
                System.out.println("New Chef added successfully.");
            } else {
                System.out.println("Something went wrong. New chef was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * Add a delivery guy into database
     * @author Yingjie Xu
     */
    public static void addDeliveryGuy() {
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            int sid = addStaff();
            if (sid == -1) {
                System.out.println("Fail to add the staff.");
                return;
            }
            System.out.println("----- Info of Delivery_guy -----");
            System.out.println("Phone number ('xxx-xxx-xxxx'): ");
            String phone = sc.nextLine();
            System.out.println("Delivery method (Car, Bike etc.): ");
            String method = sc.nextLine();
            int result = statement.executeUpdate("INSERT INTO delivery_guy VALUES('" + method + "','" + phone + "'," + sid + ");");
            if (result == 1) {
                System.out.println("New Delivery_guy added successfully.");
            } else {
                System.out.println("Something went wrong. New Delivery_guy was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * Add a waiter into database
     * @author Yingjie Xu
     */
    public static void addWaiter() {
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            int sid = addStaff();
            if (sid == -1) {
                System.out.println("Fail to add the staff.");
                return;
            }
            System.out.println("----- Info of Waiter -----");
            System.out.println("Waiter doesn't require additional attributes.");
            int result = statement.executeUpdate("INSERT INTO waiter VALUES(" + sid + ");");
            if (result == 1) {
                System.out.println("New waiter added successfully.");
            } else {
                System.out.println("Something went wrong. New waiter was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * Modify the salary of a given sid
     * @author Yingjie Xu
     */
    public static void modifySalary() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Modify salary -----");
            System.out.println("Please enter the sid: ");
            int sid = sc.nextInt();
            sc.nextLine();
            rs = statement.executeQuery("SELECT sid FROM staff WHERE sid = " + sid + ";");
            if (rs.next()) {
                System.out.println("New salary of the staff: ");
                double salary = sc.nextDouble();
                sc.nextLine();
                int result = statement.executeUpdate("UPDATE staff SET salary = " + salary + " WHERE sid = " + sid + ";");
                if (result == 1) {
                    System.out.println("New salary set successfully.");
                } else {
                    System.out.println("Something went wrong.");
                }
            } else {
                System.out.println("The sid doesn't exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * Modify the schedule of a given sid
     * @author Yingjie Xu
     */
    public static void modifySchedule() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Modify schedule -----");
            System.out.println("Please enter the sid: ");
            int sid = sc.nextInt();
            sc.nextLine();
            rs = statement.executeQuery("SELECT sid FROM staff WHERE sid = " + sid + ";");
            if (rs.next()) {
                System.out.println("New schedule of the staff: ");
                String time = sc.nextLine();
                int result = statement.executeUpdate("UPDATE staff SET working_schdule = '" + time + "' WHERE sid = " + sid + ";");
                if (result == 1) {
                    System.out.println("New schedule set successfully.");
                } else {
                    System.out.println("Something went wrong.");
                }
            } else {
                System.out.println("The sid doesn't exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * Modify the proficiency of a given sid
     * @author Yingjie Xu
     */
    public static void modifyProficiency() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Modify Proficiency -----");
            System.out.println("Please enter the sid: ");
            int sid = sc.nextInt();
            sc.nextLine();
            rs = statement.executeQuery("SELECT sid FROM chef WHERE sid = " + sid + ";");
            if (rs.next()) {
                System.out.println("New Proficiency of the chef (1 - 5): ");
                int proficiency = sc.nextInt();
                sc.nextLine();
                if (proficiency > 5 || proficiency < 0) {
                    System.out.println("The proficiency is out of range.");
                } else {
                    int result = statement.executeUpdate("UPDATE chef SET proficiency = " + proficiency + " WHERE sid = " + sid + ";");
                    if (result == 1) {
                        System.out.println("New schedule set successfully.");
                    } else {
                        System.out.println("Something went wrong.");
                    }
                }
            } else {
                System.out.println("The sid doesn't exist or this sid is not a chef.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * View all the staff info
     * @author Yingjie Xu
     */
    public static void viewAllStaff() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- View all staff -----");
            rs = statement.executeQuery("SELECT sid, sname, working_schdule, salary FROM staff ORDER BY sid");
            while (rs.next()) {
                int sid = rs.getInt("sid");
                String name = rs.getString("sname");
                String schedule = rs.getString("working_schdule");
                Double salary = rs.getDouble("salary");
                System.out.println(name + " (sid: " + sid + ") works in the " + schedule + " and the salary is " + salary + " $/hr.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
}
    }

    /**
     * All possible methods for the admin to modify the customer
     * @author Hengxian Jiang
     */
    public static void customer() {
        System.out.println("---------- Customer ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Add a customer");
        System.out.println("2 - Make a reservation");
        System.out.println("3 - Modify the address of a customer");
        System.out.println("4 - View all the customers' info");
        System.out.println("5 - View all the orders placed by a customer"); // maybe not?
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            addCustomer();
        } else if (select == 2) {
            makeReservation();
        } else if (select == 3) {
            modifyAddress();
        } else if (select == 4) {
            viewCustomerInfo();
        } else if (select == 5) {

        } else {
            System.out.println("Invalid selection.");
            return;
        }
    }

    public static void addCustomer() {
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Info of Customer -----");
            System.out.println("Phone number of the customer xxx-xxx-xxxx");
            String phoneNumber = sc.nextLine();
            System.out.println("Name of the customer: ");
            String cname = sc.nextLine();
            System.out.println("address of the customer: ");
            String address = sc.nextLine();
            int result = statement.executeUpdate("INSERT INTO customer VALUES('" + phoneNumber + "','" + cname
                    + "','" + address + "');");
                if (result == 1) {
                System.out.println("New Customer added successfully.");
            } else {
                System.out.println("Something went wrong. New customer was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public static void makeReservation(){
        // HOW TO STORE DATE DATA TYPE
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Info of Reservation -----");
            String pattern = "MM-dd-yyyy";
            System.out.println("Date of the reservation("+ pattern + ")");
            String date = sc.nextLine();
            System.out.println("Phone number of the customer xxx-xxx-xxxx");
            String phoneNumber = sc.nextLine();
            System.out.println("time slot reserved by customer xx:xx:xx");
            String timeSlot = sc.nextLine();
            int result = statement.executeUpdate("INSERT INTO reservation VALUES('" + date + "','" + phoneNumber
                    + "','" + timeSlot + "');");
            if (result == 1) {

                System.out.println("New reservation added successfully.");
            } else {
                System.out.println("Something went wrong. New reservation was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public static void modifyAddress(){
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        ResultSet addr = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Modify Address -----");
            System.out.println("Please enter the phone number: ");
            String phoneNumber = sc.nextLine();
            rs = statement.executeQuery("SELECT phone_number FROM customer WHERE phone_number = " + phoneNumber + ";");
            addr = statement.executeQuery("SELECT address FROM customer WHERE phone_number = " + phoneNumber + ";");
            if (rs.next()) {
                System.out.print("The old address is: ");
                System.out.println(addr);
                System.out.println("New address of the customer ");
                String address = sc.nextLine();
                int result = statement.executeUpdate("UPDATE customer SET address = '" + address + "' WHERE phone_number = " + phoneNumber + ";");
                if (result == 1) {
                    System.out.println("New address set successfully.");
                } else {
                    System.out.println("Something went wrong.");
                }
            } else {
                System.out.println("The phone_number doesn't exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public static void viewCustomerInfo() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- View all customer -----");
            rs = statement.executeQuery("SELECT phone_number, canme, address FROM customer ORDER BY canme");
            while (rs.next()) {
                String phone_number = rs.getString("phone_number");
                String name = rs.getString("canme");
                String address = rs.getString("address");
                System.out.println(name + " (phone_number: " + phone_number +") name: " + name + " address: " + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }


    /**
     * All possible methods for the admin to modify the menu
     * @author Hengxian Jiang
     */
    public static void menu() {
        System.out.println("---------- Menu ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Add a dish");
        System.out.println("2 - Modify the price of a dish");
        System.out.println("3 - View all the dishes");
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            addDish();
        } else if (select == 2) {
            modifyPrice();
        } else if (select == 3) {
            viewAllDish();
        } else {
            System.out.println("Invalid selection.");
            return;
        }
    }

    public static void addDish(){
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Info of Dish -----");
            System.out.println("Name of the dish: ");
            String dishName = sc.nextLine();
            System.out.println("Price of the dish: ");
            double price = sc.nextDouble();
            int result = statement.executeUpdate("INSERT INTO customer VALUES('" + dishName + "','" + price
                    + "','" + "');");
            if (result == 1) {
                System.out.println("New Customer added successfully.");
            } else {
                System.out.println("Something went wrong. New customer was not added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public static void modifyPrice(){
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        ResultSet price_d = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- Modify Dish Price -----");
            System.out.println("Please enter the Dish Name: ");
            String dishName = sc.nextLine();
            rs = statement.executeQuery("SELECT dish_name FROM dish WHERE dish_name = " + dishName + ";");
            price_d = statement.executeQuery("SELECT price FROM dish WHERE dish_name = " + dishName + ";");
            if (rs.next()) {
                System.out.print("The old price is: ");
                System.out.println(price_d);
                System.out.println("New Price for that dish: ");
                Double price = sc.nextDouble();
                int result = statement.executeUpdate("UPDATE dish SET price = '" + price + "' WHERE dish_name = " + dishName + ";");
                if (result == 1) {
                    System.out.println("New price set successfully.");
                } else {
                    System.out.println("Something went wrong.");
                }
            } else {
                System.out.println("The dish name doesn't exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public static void viewAllDish(){
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            System.out.println("----- View all dishes -----");
            rs = statement.executeQuery("SELECT dish_name, price FROM dish ORDER BY dish_name");
            while (rs.next()) {
                String dish_name = rs.getString("dish_name");
                Double price = rs.getDouble("price");
                System.out.println( " name: " + dish_name +"     $ " + price );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { statement.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    /**
     * All possible methods for the admin to modify the order
     * @author Helen Ren & Weiming Guo
     */
    public static void order() {
        System.out.println("---------- Order ----------");
        System.out.println("Please select from following options: ");
        System.out.println("1 - Place a dine-in order");
        System.out.println("2 - Place a delivery order");
        System.out.println("3 - View order by oid");
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
             Connection con = null;
            Statement statement = null;
            ResultSet rs = null;
            try{
                con = DriverManager.getConnection(url, usernamestring, passwordstring);
                statement = con.createStatement();
                rs = statement.executeQuery("SELECT order_number FROM orders ORDER BY order_number DESC LIMIT 1");
                rs.next();
                String oid = rs.getString(1);
                System.out.println(oid);
                int ioid=Integer.parseInt(oid);
                ioid++;
                oid=String.format("%03d", ioid);
                System.out.println(oid);
                System.out.println("----- Info of Orders -----");
                double tips=0.0 ;
                int result1= statement.executeUpdate("INSERT INTO orders VALUES('" + oid + "'," + tips + ");");
                if(result1==1){
                    System.out.println("New order added successfully.");
                }else{
                    System.out.println("Something went wrong. New order was not added.");
                }
                System.out.println("phone number of the customer: ");
                String phone_num = sc.nextLine();
                if(validatePhone(phone_num)){
                    rs=statement.executeQuery("SELECT * FROM customer WHERE phone_number='" + phone_num + "';");
                }else{
                    int res=statement.executeUpdate("DELETE FROM orders WHERE order_number='"+oid+ "'");
                   return;
                }
                if(rs.next()){
                    int result = statement.executeUpdate("INSERT INTO dine_in_orders VALUES('" + oid + "','" + phone_num + "');");
                    if (result == 1) {
                        System.out.println("New dine_in_order added successfully.");
                    } else {
                        System.out.println("Something went wrong. New dine_in_order was not added.");
                        SQLException e;
                    }
                }else{
                   System.out.println("please input customer name");
                   String name=sc.nextLine() ;
                    System.out.println("please input customer address");
                    String address=sc.nextLine() ;
                   int result3=statement.executeUpdate("INSERT INTO customer VALUES('" + phone_num + "','" + name + "',' "+address+" ');");
                   int result = statement.executeUpdate("INSERT INTO dine_in_orders VALUES('" + oid + "','" + phone_num + "');");
                    if (result == 1) {
                        System.out.println("New dine_in_order added successfully.");
                    } else {
                        System.out.println("Something went wrong. New dine_in_order was not added.");
                    }
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { statement.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
            }

        } else if (select == 2) {
            delivery_order();
        } else if (select == 3) {
            Connection con = null;
            Statement statement = null;
            ResultSet rs = null;
            try {
                con = DriverManager.getConnection(url, usernamestring, passwordstring);
                statement = con.createStatement();
                System.out.println("----- View order by oid -----");
                System.out.println("Please enter the oid: ");
                String oid = sc.nextLine();
                rs = statement.executeQuery("SELECT order_number,tips FROM orders WHERE order_number='" + oid + "';");
                if (rs.next()) {
                    String ordernum = rs.getString("order_number");
                    double tips = rs.getDouble("tips");
                    System.out.println(" the order with number " + ordernum + " has tips " + tips);
                } else {
                    System.out.println("Something went wrong.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { statement.close(); } catch (Exception e) { /* ignored */ }
                try { con.close(); } catch (Exception e) { /* ignored */ }
            }

        } else {
            System.out.println("Invalid selection.");
            return;
        }
    }
    
    public static void delivery_order() {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, usernamestring, passwordstring);
            statement = con.createStatement();
            //get next available oid
            rs = statement.executeQuery("SELECT order_number FROM orders ORDER BY order_number DESC LIMIT 1");
            rs.next();
            String oid = rs.getString(1);
            int ioid=Integer.parseInt(oid);
            ioid++;
            oid=Integer.toString(ioid);
            //get next available delivery_guy(Pick a delivery guy with lowest workload)
            rs = statement.executeQuery("SELECT sid, count(*) FROM delivery_orders GROUP BY sid ORDER BY sid LIMIT 1");
            rs.next();
            int sid = rs.getInt(1);
            //info of orders
            System.out.println("----- Info of Orders -----");
            //Tips
            System.out.println("Tips for the new order:");
            double tips = sc.nextDouble();
            sc.nextLine();
            //insert one record to order table
            int result = statement.executeUpdate("INSERT INTO orders VALUES('" + oid + "','" + tips + "');");
            //platform
            int count = 1;
            System.out.println("Input the name of the platform: ");
            rs = statement.executeQuery("SELECT pname FROM platform;");
            while (rs.next()) {
                String name = rs.getString("pname");
                System.out.println("" + count + ". - " + name);
                count++;
            }
            String pf = sc.nextLine();
            //delivery fee
            System.out.println("delivery_fee:");
            float dFee = sc.nextFloat();
            sc.nextLine();
            //phone number
            System.out.println("phone number of the customer: ");
            String phone_num = sc.nextLine();
            rs=statement.executeQuery("SELECT * FROM customer WHERE phone_number='" + phone_num + "';");

            if(rs.next()){
                result = statement.executeUpdate("INSERT INTO delivery_orders VALUES ('" + oid + "','" + phone_num+ "'," + sid +  ",'" + pf + "'," + dFee + ");");
                if (result == 1) {
                    System.out.println("New delivery_order added successfully.");
                } else {
                    System.out.println("Something went wrong. New delivery_order was not added.");
                }
            }else{
                System.out.println("please input customer name");
                String name=sc.nextLine() ;
                System.out.println("please input customer address");
                String address=sc.nextLine() ;
                result=statement.executeUpdate("INSERT INTO customer VALUES('" + phone_num + "','" + name + "',' "+address+" ');");
                if(result == 1){
                    System.out.println("New customer added successfully");
                }else{
                    System.out.println("Something went wrong. New customer was not added");
                }
                result = statement.executeUpdate("INSERT INTO delivery_orders" +
                        "VALUES ('" + oid + "','" + phone_num+ "'," + sid +  ",'" + pf + "'," + dFee + ");");
                if (result == 1) {
                    System.out.println("New delivery_order added successfully.");
                } else {
                    System.out.println("Something went wrong. New delivery_order was not added.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) { /* ignored */ }
            try {
                statement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                con.close();
            } catch (Exception e) { /* ignored */ }
        }
    }



}
