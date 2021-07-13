package com.zadanie.zadaniefin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZadaniefinApplicationTests {
    @Autowired
    private SessionFactory factory;
	private Logger logger = LoggerFactory.getLogger(ZadaniefinApplicationTests.class);
    @Test
	void testConnection() {
	    try{
            Session session = factory.openSession();
            Assert.assertNotNull(session);
            session.close();
        }
	    catch (Exception e){
	        logger.error("error"+e.getStackTrace().toString());
        }
	}

}
