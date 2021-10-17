package demo0908.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    private static ComboPooledDataSource dataSource;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection conn) {
        close(conn, null, null);
    }

    public static void close(Connection conn, Statement st) {
        close(conn, st, null);
    }

    public static void close(Connection conn, Statement st, ResultSet rst) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
