package edu.global.ex.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	void dataSourceTest() {
		System.out.println(dataSource); //dataSource는 Connection pull
		assertNotNull(dataSource); //왜 통과될까? -> 스프링은 톰캣을 반드시 내장되어있고 Connection pool도 반드시 포함되어있다.
	}
	
	@Test
	void testConnection() throws Exception{
		System.out.println("DS=" + dataSource);
		
		try (Connection conn = dataSource.getConnection()){ //dataSource에서 connection객체를 가져온다.
			assertNotNull(conn); //connection객체가 있는지 확인하는 코드
			System.out.println("conn=" + conn);
			assertThat(conn).isInstanceOf(Connection.class); //connection객체가 Connection.class인지 확인
			
			assertEquals(100, getLong(conn, "select 100 from dual")); //100과 getLong(conn, "select 100 from dual")의 값 비교
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private long getLong(Connection conn, String sql) {
		long result = 0;
		try (Statement stmt = conn.createStatement()){
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
