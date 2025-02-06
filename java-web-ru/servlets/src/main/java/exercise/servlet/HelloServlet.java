package exercise.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest rec, HttpServletResponse res) throws ServletException, IOException {
        var name = "";
        if (rec.getParameter("name") != null) {
            name = rec.getParameter("name");
        } else {
            name = "Guest";
        }
        var result = "Hello, " + name + "!";
        res.getWriter().write(result);
    }
    // END
}
