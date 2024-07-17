package QuizOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process the quiz submission (e.g., save to database)
        String username = request.getParameter("username");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect"));
        int timeTaken = Integer.parseInt(request.getParameter("timeTaken"));

        // Handle the quiz response here (e.g., store in database)

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\":\"Quiz response received\"}");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        // Retrieve the quiz results for the user here (e.g., from database)

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"username\":\"" + username + "\", \"score\":10, \"timeTaken\":300}");
    }
}
