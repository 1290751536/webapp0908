package demo0908.servlet;

import demo0908.dao.UserDao;
import demo0908.entity.User;
import demo0908.utils.EncryptUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class IoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");
        password = EncryptUtils.digest(password);
        String code = request.getParameter("code");
        UserDao userDao = new UserDao();

        PrintWriter out = response.getWriter();
        String outString = "登录失败，请重试...";

        HttpSession session = request.getSession();

        User user = userDao.queryById(userId);

        if (!userId.isEmpty() && !password.isEmpty() && !code.isEmpty()) {
            if (user != null && code.equalsIgnoreCase((String) session.getAttribute("code")) && user.getPassword().equals(password)) {
                outString = "登录成功...";
                session.setAttribute("user", user);
                if (request.getParameter("remember") != null) {
                    Cookie cookieUserId = new Cookie("userId", userId);
                    Cookie cookiePassword = new Cookie("password", password);
                    cookieUserId.setMaxAge(3600);
                    cookiePassword.setMaxAge(3600);
                    response.addCookie(cookieUserId);
                    response.addCookie(cookiePassword);
                }
                response.sendRedirect(request.getContextPath() + "/home/home.html");
            }
        }

        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>" + outString + "</h1>" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
