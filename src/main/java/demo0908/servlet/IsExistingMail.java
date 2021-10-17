package demo0908.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo0908.dao.MailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "IsExistingMail", value = "/is-existing-mail")
public class IsExistingMail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String retJSON = null;

        String mail = request.getParameter("mail");
//        System.out.println(mail);
        MailDao mailDao = new MailDao();
        if (mailDao.isFindMail(mail)) {
            retJSON = mapper.writeValueAsString("该邮箱已经被注册");
        }
        else{
            retJSON = mapper.writeValueAsString("该邮箱可以使用");
        }
        out.write(retJSON);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
