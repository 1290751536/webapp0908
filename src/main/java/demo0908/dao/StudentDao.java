package demo0908.dao;

import demo0908.entity.Student;
import demo0908.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rst = null;

    public List<Student> queryAllStudent() {
        List<Student> students = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("select * from student");
            rst = st.executeQuery();
            while (rst.next()) {
                Student student = new Student();
                student.setStudentId(rst.getString("student_id"));
                student.setName(rst.getString("name"));
                student.setAge(rst.getInt("age"));
                student.setTelephone(rst.getString("telephone"));
                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    public boolean deleteById(String studentId) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("delete from student where student_id = ?");
            st.setString(1, studentId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateStudentId(String oldStudentId, String newStudentId) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update student set student_id = ? where student_id = ?");
            st.setString(1, newStudentId);
            st.setString(2, oldStudentId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateName(String studentId, String name) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update student set name = ? where student_id = ?");
            st.setString(1, name);
            st.setString(2, studentId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateAge(String studentId, String age) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update student set age = ? where student_id = ?");
            st.setString(1, age);
            st.setString(2, studentId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateTelephone(String studentId, String telephone) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update student set telephone = ? where student_id = ?");
            st.setString(1, telephone);
            st.setString(2, studentId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateInfo(Student student) {
        String studentId = student.getStudentId();
        String name = student.getName();
        int age = student.getAge();
        String telephone = student.getTelephone();
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement("update student set name= ? where student_id= ?");
            st.setString(1, name);
            st.setString(2, studentId);
            st.executeUpdate();
            st = conn.prepareStatement("update student set age= ? where student_id= ?");
            st.setInt(1, age);
            st.setString(2, studentId);
            st.executeUpdate();
            st = conn.prepareStatement("update student set telephone= ? where student_id= ?");
            st.setString(1, telephone);
            st.setString(2, studentId);
            st.executeUpdate();
            flag = true; // 是否全部信息更新成功
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                JDBCUtils.close(conn, st);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }
}
