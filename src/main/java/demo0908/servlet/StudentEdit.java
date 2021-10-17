package demo0908.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo0908.dao.StudentDao;
import demo0908.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StudentEdit", value = "/edit")
public class StudentEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 返回信息
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String resJSON = null, msg = null;
        System.out.println(request.getParameter("age"));
        // 获取更新参数
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String telephone = request.getParameter("telephone");
        Student student = new Student();
        student.setStudentId(studentId);
        student.setName(name);
        student.setAge(age);
        student.setTelephone(telephone);
        System.out.println(student);
        // 执行更新操作
        StudentDao studentDao = new StudentDao();
        if (studentDao.updateInfo(student)) {
            msg = "更新成功";
        } else {
            msg = "更新失败";
        }

        // 带回更新结果
        resJSON = mapper.writeValueAsString(msg);
        out.write(resJSON);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
