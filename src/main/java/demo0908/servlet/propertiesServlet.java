package demo0908.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "properties", value = "/properties")
public class propertiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Properties properties = new Properties();
        properties.load(propertiesServlet.class.getResourceAsStream("/db.properties"));
        System.out.println();
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + properties.getProperty("dirver") + "</h1>");
        out.println("</body></html>");
    }
}
