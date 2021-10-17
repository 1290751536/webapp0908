package demo0908.filter;

import demo0908.dao.UserDao;
import demo0908.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "IsLoginFilter", value = "/home/*")
public class IsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            chain.doFilter(req, resp);
        } else {
            String userId = null, password = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userId".equals(cookie.getName()))
                        userId = cookie.getValue();
                    else if ("password".equals(cookie.getName()))
                        password = cookie.getValue();
                }
            }
            if (userId != null && password != null) {
                UserDao userDao = new UserDao();
                user = userDao.queryById(userId);
                if (user != null && user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                    chain.doFilter(req, resp);
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.html");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
