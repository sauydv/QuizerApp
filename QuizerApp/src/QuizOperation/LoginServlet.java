package QuizOperation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection connection = DatabaseConnection.getConnection();
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Consider hashing the password
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                response.sendRedirect("welcome.html");
            } else {
                response.sendRedirect("login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.html");
        }
        
    

    }
    
}


