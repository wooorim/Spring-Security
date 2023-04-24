package edu.global.ex.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest //모든 bean들을 스캔하고 어플리케이션 컨텍스트를 생성하여 테스트를 실행한다.
class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void testInserAdmin() {
		UserVO user = new UserVO();
		user.setUsername("admin4");
		user.setPassword(passwordEncoder.encode("1234"));
		user.setEnabled(1);
	
		//1:N관계이기 때문에 2개 필요
		userMapper.insertUser(user);
		userMapper.insertAdminAuthorities(user);
	}
	
	
	
// @Test 
//	void testInserUser() {
//		UserVO user = new UserVO();
//		user.setUsername("kim5");
//		user.setPassword(passwordEncoder.encode("1234"));
//		user.setEnabled(1);
//
//		userMapper.insertUser(user);
//	}

//	@Test 
//	void testInserUser() {
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
//	}

	@Autowired
	private UserVO userVO;
	
	@Disabled //테스트 하지마라라는 어노테이션
	void testpassWordEncoder() {

		String plainPW = "1234";
		String encodedPW = new BCryptPasswordEncoder().encode(plainPW); //BCryptPasswordEncoder() -> 암호화 모듈
		
		System.out.println(plainPW + ":" + encodedPW);
		System.out.println(passwordEncoder.matches(plainPW, encodedPW));
		System.out.println(userVO);
		
		
		//=============================
		//JUnit이 제공하는 함수
		assertNotEquals(plainPW, encodedPW);
		assertEquals(plainPW, encodedPW);
		
		assertTrue(new BCryptPasswordEncoder().matches(plainPW, encodedPW));
	}

}
