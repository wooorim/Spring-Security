package edu.global.ex.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

//	@Test
//	void testInserUser() {
//
////	      @Insert("insert into users(username,password,enabled) values(#{username},#{password},#{enabled})")
////	      public int insertUser(UserVO userVO);
//		//
////	      @Insert("insert into AUTHORITIES (username,AUTHORITY) values(#{username},'ROLE_USER')")
////	      public void insertAuthorities(UserVO UserVO);
//
//		UserVO user = new UserVO();
//		user.setUsername("kim3");
//		user.setPassword(new BCryptPasswordEncoder().encode("1234"));
//		user.setEnabled(1);
//
//		userMapper.insertUser(user);
//		userMapper.insertAuthorities(user);
//
//	}
//
//	@Test
//	void testInserAdmin() {
//
//		UserVO user = new UserVO();
//		user.setUsername("admin2");
//		user.setPassword(new BCryptPasswordEncoder().encode("admin2"));
//		user.setEnabled(1);
//
//		userMapper.insertUser(user);
//		userMapper.insertAdminAuthorities(user);
//
//	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserVO userVO;
	
	@Test
	void testpassWordEncoder() {

		String plainPW = "1234";
		String encodedPW = new BCryptPasswordEncoder().encode(plainPW); //BCryptPasswordEncoder() -> 암호화 모듈
		
		System.out.println(plainPW + ":" + encodedPW);
		System.out.println(passwordEncoder.matches(plainPW, encodedPW));
		//System.out.println(userVO);
		
		
		//=============================
		//JUnit이 제공하는 함수
		//assertNotEquals(plainPW, encodedPW);
		assertEquals(plainPW, encodedPW);
		
		assertTrue(new BCryptPasswordEncoder().matches(plainPW, encodedPW));
		
	}

}
