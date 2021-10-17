package demo0908.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class MailDaoTest {

    @Test
    public void isFindMail() {
        MailDao mailDao = new MailDao();
        if(mailDao.isFindMail("129075136@qq.com")){
            System.out.println(666);
        }
    }
}