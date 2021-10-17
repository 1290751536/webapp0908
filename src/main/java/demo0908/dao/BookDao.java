package demo0908.dao;

import demo0908.utils.JDBCUtils;
import demo0908.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public List<Book> queryBookByName(String name) {
        List<Book> res = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rst = null;
        try {
            conn = JDBCUtils.getConnection();
            name = '%' + name + '%';
            st = conn.prepareStatement("SELECT * FROM books WHERE book_name LIKE  ?");
            st.setString(1, name);
            rst = st.executeQuery();
            while (rst.next()) {
                Book book = new Book();
                book.setBookId(rst.getInt("book_id"));
                book.setBookName(rst.getString("book_name"));
                book.setBookPrice(rst.getFloat("price"));
                res.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, st, rst);
        }
        return res;
    }
}
