import  java.net.ConnectException;
import java.security.spec.ECField;
import java.sql.*;
import java.util.Scanner;

    public class ProjectOne {
        static Scanner sc = new Scanner(System.in);
        static Statement statement;

        void createTable() throws Exception {
//        Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "11111111");) {
                statement = con.createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS data(Name Text NOT NULL, Email Text,Age int, location Text, Designation Text)");
                System.out.println("Database created");
            }


        }

         int populateTable() {

             String Name, Email, Location, Designation, answer;
             int age;
             String name, email, location, designation;
             int Age, count;
             count = 0;

             try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_one", "root", "11111111");) {
                 ProjectOne project = new ProjectOne();

                 project.createTable();


                 statement = con.createStatement();



                 PreparedStatement inserStatement = con.prepareStatement("INSERT INTO data(Name,Email,Age,Location,Designation)VALUES(?,?,?,?,?)");
                 do {
                     System.out.println("please enter your name ");
                     Name = sc.nextLine();
                     System.out.println("Please enter your Email");
                     Email = sc.nextLine();
                     System.out.println("Please enter your age");
                     Age = sc.nextInt();
                     sc.nextLine();
                     System.out.println("please enter your location");
                     Location = sc.nextLine();
                     System.out.println("please enter your Designation");
                     Designation = sc.nextLine();

                     inserStatement.setString(1, Name);
                     inserStatement.setString(2, Email);
                     inserStatement.setInt(3, Age);
                     inserStatement.setString(4, Location);
                     inserStatement.setString(5, Designation);
                     inserStatement.execute();

                     //System.out.println("Do you want to insert more data");
                     //answer = sc.nextLine();


                     count++;

                 } while (count < 10);
                 return count;


             } catch (Exception e) {
                 System.out.println(e.getMessage());
             }

             return 0;
         }



        public static void main(String[] args) {
            ProjectOne project1 = new ProjectOne();
            project1.populateTable();

        }
    }
