package Library_Managment;
import java.sql.*;

public class Books {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3308/library_system",
                    "root", "116081Gb.");




            Statement statement ;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from Books");
            int code;
            String title;

            while(resultSet.next()){
                int bookID = resultSet.getInt("book_id");
                String bookName = resultSet.getString("book_name");
                String studentNAme = resultSet.getString("student_name");
                int rollNo = resultSet.getInt("Student_id");
                Date issueDate = resultSet.getDate("issue_date");
                String bookType = resultSet.getString("book_type");


                System.out.println("Book id :- " + bookID);
                System.out.println("Book Name :- " + bookName);
                System.out.println("Student Name:- " + studentNAme);
                System.out.println("Student Id :- " + rollNo);
                System.out.println("Issue Date :- " + issueDate);
                System.out.println("Book Type :- " + bookType);
                System.out.println("_______________________________________");
            }


        } catch (Exception e){
            System.out.println(e);
        }
    }
}
