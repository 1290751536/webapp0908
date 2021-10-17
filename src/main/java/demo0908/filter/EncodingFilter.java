package demo0908.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(value = "/*")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();
        //System.out.println(url);
        if (url.contains(".") || url.equals("")) {
            chain.doFilter(req, resp);
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
