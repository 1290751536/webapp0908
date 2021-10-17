package demo0908.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDaoTest {

    @Test
    public void queryAllStudent() {
        StudentDao studentDao = new StudentDao();
        System.out.println(studentDao.queryAllStudent());
    }
}