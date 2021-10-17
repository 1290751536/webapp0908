package demo0908.dao;

import demo0908.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MailDao {
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rst = null;

    public boolean isFindMail(String mail) {
        boolean flag = false;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("select * from db1 where mail=?");
            st.setString(1, mail);
            rst = st.executeQuery();
            if (rst.next()) {
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }
}
