package demo0908.dao;

import demo0908.utils.JDBCUtils;
import demo0908.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rst = null;

    public boolean insertUser(User user) {
        Connection conn = null;
        PreparedStatement st = null;
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("insert into user_t(user_id,password,user_name) value (?,?,?)");
            st.setString(1, user.getUserId());
            st.setString(2, user.getPassword());
            st.setString(3, user.getUserName());
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(conn, st);
        }
        return num > 0;
    }

    public User queryById(String userId) {
        User user = null;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("select * from user_t where user_id = ?");
            st.setString(1, userId);
            rst = st.executeQuery();
            if (rst.next()) {
                user = new User();
                user.setUserId(rst.getString("user_id"));
                user.setUserName(rst.getString("user_name"));
                user.setPassword(rst.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(conn, st, rst);
        }
        return user;
    }

    public String findUserAvatar(String userId) {
        String userAvatar = null;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("select * from user_t where user_id = ?");
            st.setString(1, userId);
            rst = st.executeQuery();
            if (rst.next()) {
                userAvatar = rst.getString("avatar");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.close(conn, st, rst);
        }
        return userAvatar;
    }

    public boolean updateUserName(String userId, String userName) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update user_t set user_name = ? where user_id = ?");
            st.setString(1, userName);
            st.setString(2, userId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updatePassword(String userId, String password) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update user_t set password = ? where user_id = ?");
            st.setString(1, password);
            st.setString(2, userId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }

    public boolean updateAvatar(String userId, String avatar) {
        int num = 0;
        try {
            conn = JDBCUtils.getConnection();
            st = conn.prepareStatement("update user_t set avatar = ? where user_id = ?");
            st.setString(1, avatar);
            st.setString(2, userId);
            num = st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return num > 0;
    }
}
