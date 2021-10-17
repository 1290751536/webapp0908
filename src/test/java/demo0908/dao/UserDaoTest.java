package demo0908.dao;

import demo0908.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setUserName("李四");
        user.setPassword("123asd");
        UserDao userDao = new UserDao();
        userDao.insertUser(user);
    }

    @Test
    public void queryById() {
        System.out.println(new UserDao().queryById("1290751536"));
    }
}