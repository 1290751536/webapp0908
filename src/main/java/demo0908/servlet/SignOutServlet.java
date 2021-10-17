package demo0908.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SignOutServlet", value = "/signout")
public class SignOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        // 通过持续时间为0删除Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String userId = null, password = null;
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName()))
                    userId = cookie.getValue();
                if ("password".equals(cookie.getName()))
                    password = cookie.getValue();
            }
            if (userId != null && password != null) {
                Cookie cookieId = new Cookie("userId", null);
                cookieId.setMaxAge(0);
                Cookie cookiePassword = new Cookie("password", null);
                cookiePassword.setMaxAge(0);
                response.addCookie(cookieId);
                response.addCookie(cookiePassword);
            }
        }
    }
}
