package edu.global.ex.vo;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	void dataSourceTest() {
		System.out.println(dataSource); //dataSource는 Connection pull
		assertNotNull(dataSource); //왜 통과될까?
	}

}
