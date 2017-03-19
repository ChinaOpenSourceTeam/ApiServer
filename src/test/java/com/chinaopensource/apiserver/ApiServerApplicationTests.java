package com.chinaopensource.apiserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
//@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)  
//@Transactional
@SpringBootTest
public class ApiServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
