package QuizOperation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String academicInstitution = request.getParameter("academic_institution");
        String fieldOfStudy = request.getParameter("field_of_study");
        String yearOfStudy = request.getParameter("year_of_study");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Debugging: Check for null parameters
        if (name == null || email == null || academicInstitution == null || fieldOfStudy == null || yearOfStudy == null || username == null || password == null || confirmPassword == null) {
            System.out.println("One or more parameters are null");
            response.sendRedirect("signup.html");
            return;
        }

        // Password confirmation check
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("signup.html");
            return;
        }

        Connection connection = DatabaseConnection.getConnection();
        // Debugging: Check for null connection
        if (connection == null) {
            System.out.println("Failed to establish connection to the database.");
            response.sendRedirect("signup.html");
            return;
        }

        try {
            String query = "INSERT INTO users (name, email, academic_institution, field_of_study, year_of_study, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, academicInstitution);
            pstmt.setString(4, fieldOfStudy);
            pstmt.setString(5, yearOfStudy);
            pstmt.setString(6, username);
            pstmt.setString(7, password); // Consider hashing the password
            pstmt.executeUpdate();
            response.sendRedirect("login.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.html");
        }
    }
}
