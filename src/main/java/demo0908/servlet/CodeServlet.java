package demo0908.servlet;

import demo0908.utils.CodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "code-servlet", value = "/code-servlet")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map map = CodeUtils.generateCodeAndPic();
        String code = map.get("code").toString();
        BufferedImage img = (BufferedImage) map.get("codePic");
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(img, "jpeg", out);
    }
}
