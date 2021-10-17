package demo0908.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo0908.dao.StudentDao;
import demo0908.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        String studentId = request.getParameter("studentId");
        System.out.println(studentId);
        StudentDao studentDao = new StudentDao();
        ObjectMapper mapper = new ObjectMapper();
        String resJSON = null,msg = null;
        if(studentDao.deleteById(studentId)){
            msg = "删除成功";
        }
        else{
            msg = "删除失败";
        }
        resJSON = mapper.writeValueAsString(msg);
        out.write(resJSON);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
