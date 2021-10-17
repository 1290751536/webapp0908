package demo0908.servlet;

import demo0908.dao.UserDao;
import demo0908.entity.User;
import demo0908.utils.EncryptUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userid");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        PrintWriter out = response.getWriter();
        String outString = "注册失败，请重试...";

        HttpSession session = request.getSession();

        if (!userName.isEmpty() && !userId.isEmpty() && !password.isEmpty() && !code.isEmpty() && code.equalsIgnoreCase((String) session.getAttribute("code"))) {
            // 密码加密
            password = EncryptUtils.digest(password);

            User user = new User(userId, password, userName);
            UserDao userDao = new UserDao();

            if (userDao.insertUser(user)) {
                outString = "注册成功...3s后回到登录界面";
                response.setHeader("refresh","3;url=" + request.getContextPath() + "/login.html");
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
