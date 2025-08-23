package Library_Managment;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Books {
    public static void main(String[] args) {
        Connection connection = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3308/library_system",
                    "root", "116081Gb.");

            Statement statement ;
            statement = connection.createStatement();
            Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println("Select One Option From Below:- ");
                System.out.println("1. To data retrieval");
                System.out.println("2. To issue book");
                System.out.println("3. Book returned delete data");

                System.out.print("Enter Your choice: ");
                int nextInt = sc.nextInt();
                System.out.println();

                switch (nextInt) {

                    case 1:
                        int i = 1;
                        ResultSet resultSet;
                        resultSet = statement.executeQuery("select * from Books");
                        String title;

                        while (resultSet.next()) {
                            int bookID = resultSet.getInt("book_id");
                            String bookName = resultSet.getString("book_name");
                            String studentName = resultSet.getString("student_name");
                            int rollNo = resultSet.getInt("Student_id");
                            Date issueDate = resultSet.getDate("issue_date");
                            String bookType = resultSet.getString("book_type");

                            System.out.println("Student No:- " + i);
                            System.out.println("Book id :- " + bookID);
                            System.out.println("Book Name :- " + bookName);
                            System.out.println("Student Name:- " + studentName);
                            System.out.println("Student Id :- " + rollNo);
                            System.out.println("Issue Date :- " + issueDate);
                            System.out.println("Book Type :- " + bookType);
                            System.out.println("_______________________________________");
                            i++;
                        }
                        break;

                    case 2:
                        System.out.print("Enter Book Id: ");
                        int book_id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Book Name: ");
                        String book_name = sc.nextLine();

                        System.out.print("Enter Student Name: ");
                        String student_name = sc.next();
                        sc.nextLine();

                        System.out.print("Enter Student Id: ");
                        int student_id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Issue Date (DD/MM/YYYY): ");
                        String date = sc.nextLine();

                        System.out.print("Enter Book type: ");
                        String book_type = sc.next();
                        sc.nextLine();

                        SimpleDateFormat userFormat = new SimpleDateFormat("dd/MM/yyyy");
                        userFormat.setLenient(false);

                        java.util.Date utilDate = userFormat.parse(date);// java.util.Date
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // sql.Date for DB


                        String sql = "INSERT INTO Books(book_id, book_name, student_name, student_id, issue_date, book_type) " +
                                " VALUES(?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = connection.prepareStatement(sql);

                        ps.setInt(1, book_id);
                        ps.setString(2, book_name);
                        ps.setString(3, student_name);
                        ps.setInt(4, student_id);
                        ps.setDate(5, sqlDate);
                        ps.setString(6, book_type);
                        ps.executeUpdate();
                        System.out.println("1 row added successfully");
                        break;

                    case 3:
                        System.out.print("Enter Book Id: ");
                        int id = sc.nextInt();
                        sc.nextLine();  // consume newline

                        String deleteQuery = "DELETE FROM Books WHERE book_id = ?";
                        try (PreparedStatement p = connection.prepareStatement(deleteQuery)) {
                            p.setInt(1, id);
                            int rowsDeleted = p.executeUpdate();

                            if (rowsDeleted > 0) {
                                System.out.println("Book with ID " + id + " deleted successfully.");
                            } else {
                                System.out.println("No book found with ID " + id + ".");
                            }
                        } catch (SQLException e) {
                            System.out.println("Error deleting book: " + e.getMessage());
                        }
                        System.out.println();
                        break;

                    default:
                        System.out.println("Invalid Option Selected.");
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
