package demo0908.dao;

import demo0908.entity.Book;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    @Test
    public void queryBookByName() {
        BookDao bookDao = new BookDao();
        List<Book> res = bookDao.queryBookByName("Python");
        for (Book book : res) {
            System.out.println(book);
        }
    }
}