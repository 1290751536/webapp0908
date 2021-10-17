package demo0908.servlet;

import demo0908.dao.UserDao;
import demo0908.entity.User;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(value = "/userAvatarServlet")
public class userAvatarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao = new UserDao();
        String userAvatar = userDao.findUserAvatar(user.getUserId());

        if(userAvatar == null){
            userAvatar = request.getServletContext().getRealPath("/WEB-INF/user-avatar")+ "/default.png";
        }
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        FileInputStream fis = new FileInputStream(userAvatar);
        byte data[] = new byte[1024];
        while(fis.read(data) != -1){
            out.write(data);
        }
        fis.close();
        out.close();
    }
}
